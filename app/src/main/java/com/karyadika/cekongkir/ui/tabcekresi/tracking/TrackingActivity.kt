package com.karyadika.cekongkir.ui.tabcekresi.tracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.databinding.ActivityTrackingBinding
import com.karyadika.cekongkir.ui.tabcekresi.waybill.WaybillFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TrackingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val viewModelFactory: TrackingViewModelFactory by instance()
    private lateinit var viewModel: TrackingViewModel

    private lateinit var binding: ActivityTrackingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(TrackingViewModel::class.java)
    }

    private fun setupView() {
        supportActionBar!!.title = "Lacak No Resi"
    }

    override fun onBackPressed() {
        val isTracking = intent.getBooleanExtra(WaybillFragment.IS_TRACKING, false)
        if (isTracking) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}