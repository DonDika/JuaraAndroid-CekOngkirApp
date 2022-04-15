package com.karyadika.cekongkir.data.remote.response

import com.google.gson.annotations.SerializedName

data class SubdistrictResponse(
	@field:SerializedName("rajaongkir")
	val rajaongkir: Rajaongkir
)

data class Rajaongkir(
	@field:SerializedName("query")
	val query: Query,

	@field:SerializedName("results")
	val results: List<SubdistrictsItem>,

	@field:SerializedName("status")
	val status: Status
)

data class Query(
	@field:SerializedName("city")
	val city: String
)

data class SubdistrictsItem(
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
	val subdistrictName: String


)