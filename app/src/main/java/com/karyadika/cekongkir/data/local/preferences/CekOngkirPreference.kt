package com.karyadika.cekongkir.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

private const val prefName = "DonDikaCekOngkir.pref"

class CekOngkirPreference(context: Context) {

    private var sharedPref: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor

    init {
        editor = sharedPref.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String) : String? {
        return sharedPref.getString(key, null)
    }

}