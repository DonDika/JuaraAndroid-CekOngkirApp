package com.karyadika.cekongkir.data.remote.retrofit

import com.karyadika.cekongkir.data.remote.response.CityResponse
import com.karyadika.cekongkir.data.remote.response.CostResponse
import com.karyadika.cekongkir.data.remote.response.SubdistrictResponse
import com.karyadika.cekongkir.data.remote.response.WaybillResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("city")
    suspend fun getCity(): Response<CityResponse>

    @GET("subdistrict")
    suspend fun getSubdistrict(
        @Query("city") city: String
    ): Response<SubdistrictResponse>

    @FormUrlEncoded
    @POST("cost")
    suspend fun sendCost(
        @Field("origin") origin: String,
        @Field("originType") originType: String,
        @Field("destination") destination: String,
        @Field("destinationType") destinationType: String,
        @Field("weight") weight: String,
        @Field("courier") courier: String
    ): Response<CostResponse>

    @FormUrlEncoded
    @POST("waybill")
    suspend fun waybill(
        @Field("waybill") waybill: String,
        @Field("courier") courier: String
    ): Response<WaybillResponse>


}