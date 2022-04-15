package com.karyadika.cekongkir.ui.tabcekongkir.city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.R
import com.karyadika.cekongkir.utils.Resource
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import timber.log.Timber

class CityActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: CityViewModelFactory by instance()
    private lateinit var viewModel: CityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)

        setupViewModel()
        setupObserver()

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, factory).get(CityViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.titleBar.observe(this) { title ->
            supportActionBar!!.title = title
        }
        viewModel.cityResponse.observe(this) {
            when(it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {

                }
                is Resource.Error -> {

                }
            }
        }
    }


}