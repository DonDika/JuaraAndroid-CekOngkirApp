package com.karyadika.cekongkir.data.local.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableWaybill")
data class WaybillEntity (
    @PrimaryKey(autoGenerate = false)
    val waybill: String,
    val courier: String? = "",
    val status: String? = "",
)