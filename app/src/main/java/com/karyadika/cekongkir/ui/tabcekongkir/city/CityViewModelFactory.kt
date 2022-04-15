package com.karyadika.cekongkir.ui.tabcekongkir.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.data.CekOngkirRepository

class CityViewModelFactory(private val repository: CekOngkirRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

}
