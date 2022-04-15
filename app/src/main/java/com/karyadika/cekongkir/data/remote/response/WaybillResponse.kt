package com.karyadika.cekongkir.data.remote.response

import com.google.gson.annotations.SerializedName

data class WaybillResponse(
	@field:SerializedName("rajaongkir")
	val waybillRajaOngkir: WaybillRajaongkir
)

data class WaybillRajaongkir(
	@field:SerializedName("query")
	val query: QueryWaybill,

	@field:SerializedName("result")
	val result: ResultWaybill,

	@field:SerializedName("status")
	val status: Status
)

data class QueryWaybill(
	@field:SerializedName("waybill")
	val waybill: String,

	@field:SerializedName("courier")
	val courier: String
)

data class ResultWaybill(
	@field:SerializedName("delivered")
	val delivered: Boolean,

	@field:SerializedName("summary")
	val summary: Summary,

	@field:SerializedName("details")
	val details: Details,

	@field:SerializedName("delivery_status")
	val deliveryStatus: DeliveryStatus,

	@field:SerializedName("manifest")
	val manifest: List<ManifestItem>,
)

data class Summary(
	@field:SerializedName("courier_code")
	val courierCode: String,

	@field:SerializedName("courier_name")
	val courierName: String,

	@field:SerializedName("waybill_number")
	val waybillNumber: String,

	@field:SerializedName("waybill_date")
	val waybillDate: String,

	@field:SerializedName("shipper_name")
	val shipperName: String,

	@field:SerializedName("receiver_name")
	val receiverName: String,

	@field:SerializedName("origin")
	val origin: String,

	@field:SerializedName("destination")
	val destination: String,

	@field:SerializedName("service_code")
	val serviceCode: String,

	@field:SerializedName("status")
	val status: String
)

data class Details(
	@field:SerializedName("waybill_number")
	val waybillNumber: String,

	@field:SerializedName("waybill_date")
	val waybillDate: String,

	@field:SerializedName("waybill_time")
	val waybillTime: String,

	@field:SerializedName("weight")
	val weight: String,

	@field:SerializedName("origin")
	val origin: String,

	@field:SerializedName("destination")
	val destination: String,

	@field:SerializedName("shippper_name")
	val shippperName: String,

	@field:SerializedName("shipper_address1")
	val shipperAddress1: String,

	@field:SerializedName("shipper_address2")
	val shipperAddress2: String,

	@field:SerializedName("shipper_address3")
	val shipperAddress3: String,

	@field:SerializedName("shipper_city")
	val shipperCity: String,

	@field:SerializedName("receiver_name")
	val receiverName: String,

	@field:SerializedName("receiver_address1")
	val receiverAddress1: String,

	@field:SerializedName("receiver_address2")
	val receiverAddress2: String,

	@field:SerializedName("receiver_address3")
	val receiverAddress3: String,

	@field:SerializedName("receiver_city")
	val receiverCity: String,
)

data class DeliveryStatus(
	@field:SerializedName("status")
	val status: String,

	@field:SerializedName("pod_receiver")
	val podReceiver: String,

	@field:SerializedName("pod_date")
	val podDate: String,

	@field:SerializedName("pod_time")
	val podTime: String
)

data class ManifestItem(
	@field:SerializedName("manifest_description")
	val manifestDescription: String,

	@field:SerializedName("manifest_date")
	val manifestDate: String,

	@field:SerializedName("manifest_time")
	val manifestTime: String,

	@field:SerializedName("city_name")
	val cityName: String
)

