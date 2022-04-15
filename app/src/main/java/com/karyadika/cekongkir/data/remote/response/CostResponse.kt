package com.karyadika.cekongkir.data.remote.response

import com.google.gson.annotations.SerializedName

data class CostResponse(
	@field:SerializedName("rajaongkir")
	val costRajaOngkir: CostRajaOngkir
)

data class CostRajaOngkir(

	@field:SerializedName("query")
	val costQuery: CostQuery,

	@field:SerializedName("origin_details")
	val originDetails: OriginDetails,

	@field:SerializedName("destination_details")
	val destinationDetails: DestinationDetails,

	@field:SerializedName("results")
	val results: List<ResultsItem>,

	@field:SerializedName("status")
	val status: Status
)

data class CostQuery(

	@field:SerializedName("origin")
	val origin: String,

	@field:SerializedName("originType")
	val originType: String,

	@field:SerializedName("destination")
	val destination: String,

	@field:SerializedName("destinationType")
	val destinationType: String,

	@field:SerializedName("weight")
	val weight: Int,

	@field:SerializedName("courier")
	val courier: String,

)

data class OriginDetails(

	@field:SerializedName("city_id")
	val cityId: String,

	@field:SerializedName("province_id")
	val provinceId: String,

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("city_name")
	val cityName: String,

	@field:SerializedName("postal_code")
	val postalCode: String,


)

data class DestinationDetails(

	@field:SerializedName("subdistrict_id")
	val subdistrictId: String,

	@field:SerializedName("province_id")
	val provinceId: String,

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("city_id")
	val cityId: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("subdistrict_name")
	val subdistrictName: String,

)

data class ResultsItem(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("costs")
	val costs: List<CostsItem>

)

data class CostsItem(

	@field:SerializedName("service")
	val service: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("cost")
	val cost: List<CostItem>

)

data class CostItem(

	@field:SerializedName("value")
	val value: Int,

	@field:SerializedName("etd")
	val etd: String,

	@field:SerializedName("note")
	val note: String

)

