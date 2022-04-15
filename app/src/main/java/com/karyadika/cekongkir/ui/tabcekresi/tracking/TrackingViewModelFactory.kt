package com.karyadika.cekongkir.ui.tabcekresi.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.data.CekOngkirRepository

class TrackingViewModelFactory(private val repository: CekOngkirRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackingViewModel(repository) as T
    }

}