package com.karyadika.cekongkir.data

import com.karyadika.cekongkir.data.local.persistence.CekOngkirDatabase
import com.karyadika.cekongkir.data.local.persistence.WaybillEntity
import com.karyadika.cekongkir.data.local.preferences.*
import com.karyadika.cekongkir.data.remote.retrofit.ApiService

class CekOngkirRepository(private val api: ApiService,
                          private val pref: CekOngkirPreference,
                          private val db: CekOngkirDatabase) {

    suspend fun fetchCity() = api.getCity()

    suspend fun fetchSubdistrict(cityId: String) = api.getSubdistrict(cityId)

    fun savePreferences(type: String, id: String, name: String) {
        when(type) {
            "origin" -> {
                pref.put(PREF_ORIGIN_ID, id)
                pref.put(PREF_ORIGIN_NAME, name)
            }
            "destination" -> {
                pref.put(PREF_DESTINATION_ID, id)
                pref.put(PREF_DESTINATION_NAME, name)
            }
        }
    }

    fun getPreferences(): List<PreferencesModel> {
        return listOf(
            PreferencesModel("origin", pref.getString(PREF_ORIGIN_ID), pref.getString(PREF_ORIGIN_NAME)),
            PreferencesModel("destination", pref.getString(PREF_ORIGIN_ID), pref.getString(PREF_DESTINATION_NAME))
        )
    }

    suspend fun fetchCost(origin: String, originType: String, destination: String,
                          destinationType: String, weight: String, courier: String) =
        api.sendCost(origin, originType, destination, destinationType, weight, courier)


    suspend fun fetchWaybill(waybill: String, courier: String) = api.waybill(waybill, courier)


    suspend fun saveWaybill(waybillEntity: WaybillEntity){
        db.waybillDao().insert(waybillEntity)
    }

    fun getWaybill() = db.waybillDao().select()

    suspend fun deleteWaybill(waybillEntity: WaybillEntity){
        db.waybillDao().delete(waybillEntity)
    }

}