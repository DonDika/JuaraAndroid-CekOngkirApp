package com.karyadika.cekongkir.ui.tabcekresi.tracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karyadika.cekongkir.data.CekOngkirRepository
import com.karyadika.cekongkir.utils.Resource
import com.karyadika.cekongkir.data.local.persistence.WaybillEntity
import com.karyadika.cekongkir.data.remote.response.DeliveryStatus
import com.karyadika.cekongkir.data.remote.response.QueryWaybill
import com.karyadika.cekongkir.data.remote.response.WaybillResponse
import kotlinx.coroutines.launch

class TrackingViewModel(val repository: CekOngkirRepository) : ViewModel() {

    val waybillResponse: MutableLiveData<Resource<WaybillResponse>> = MutableLiveData()

    val getWaybill: LiveData<List<WaybillEntity>> = repository.getWaybill()

    fun fetchWaybill(waybill: String, courier: String) = viewModelScope.launch {
        waybillResponse.value = Resource.Loading()
        try {
            val response = repository.fetchWaybill(waybill, courier)
            val waybill = response.body()!!.waybillRajaOngkir.query
            val status = response.body()!!.waybillRajaOngkir.result.deliveryStatus
            waybillResponse.value = Resource.Success(response.body()!!)
            saveWaybill(waybill, status)
        } catch (e: Exception) {
            waybillResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun saveWaybill(waybill: QueryWaybill, status: DeliveryStatus) = viewModelScope.launch {
        repository.saveWaybill(WaybillEntity(waybill = waybill.waybill, courier = waybill.courier, status = status.status ))
    }

    fun deleteWaybill(waybill: WaybillEntity) = viewModelScope.launch {
        repository.deleteWaybill(waybill)
    }

}