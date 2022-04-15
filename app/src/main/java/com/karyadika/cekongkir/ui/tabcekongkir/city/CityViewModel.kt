package com.karyadika.cekongkir.ui.tabcekongkir.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karyadika.cekongkir.data.CekOngkirRepository
import com.karyadika.cekongkir.utils.Resource
import com.karyadika.cekongkir.data.remote.response.CityResponse
import com.karyadika.cekongkir.data.remote.response.SubdistrictResponse
import kotlinx.coroutines.launch

class CityViewModel(private val repository: CekOngkirRepository) : ViewModel() {

    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val cityResponse: MutableLiveData<Resource<CityResponse>> = MutableLiveData()
    val subdistrictResponse: MutableLiveData<Resource<SubdistrictResponse>> = MutableLiveData()

    init {
        fetchCity()
    }

    fun fetchCity() = viewModelScope.launch {
        cityResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCity()
            cityResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            cityResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun fetchSubdistrict(cityId: String) = viewModelScope.launch {
        subdistrictResponse.value = Resource.Loading()
        try {
            val response = repository.fetchSubdistrict(cityId)
            subdistrictResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            subdistrictResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun savePreferences(type: String, id: String, name: String) {
        repository.savePreferences(type, id, name)
    }

}

