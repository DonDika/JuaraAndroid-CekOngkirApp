package com.karyadika.cekongkir.data.remote.response

import com.google.gson.annotations.SerializedName

data class CityResponse(
	@field:SerializedName("rajaongkir")
	val rajaongkir: RajaOngkir
)

data class RajaOngkir(
	@field:SerializedName("query")
	val query: List<Any>,

	@field:SerializedName("results")
	val results: List<CitiesItem>,

	@field:SerializedName("status")
	val status: Status
)

data class CitiesItem(
	@field:SerializedName("city_name")
	val cityName: String,

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("province_id")
	val provinceId: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("postal_code")
	val postalCode: String,

	@field:SerializedName("city_id")
	val cityId: String
)

data class Status(
	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("description")
	val description: String
)
