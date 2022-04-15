package com.karyadika.cekongkir.ui.tabcekongkir.cost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.data.CekOngkirRepository

class CostViewModelFactory(private val repository: CekOngkirRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CostViewModel::class.java)){
            return CostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

}