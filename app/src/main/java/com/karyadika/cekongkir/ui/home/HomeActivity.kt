package com.karyadika.cekongkir.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.karyadika.cekongkir.R
import com.karyadika.cekongkir.databinding.ActivityHomeBinding
import com.karyadika.cekongkir.ui.tabcekongkir.cost.CostViewModel
import com.karyadika.cekongkir.ui.tabcekongkir.cost.CostViewModelFactory
import com.karyadika.cekongkir.ui.tabcekresi.tracking.TrackingViewModel
import com.karyadika.cekongkir.ui.tabcekresi.tracking.TrackingViewModelFactory
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val costFactory: CostViewModelFactory by instance()
    private val trackingFactory: TrackingViewModelFactory by instance()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupTab()
        setupViewModel()

    }

    private fun setupViewModel() {
        ViewModelProvider(this, costFactory).get(CostViewModel::class.java)
        ViewModelProvider(this, trackingFactory).get(TrackingViewModel::class.java)
    }

    private fun setupTab() {
        val homeTabAdapter = HomeTabAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = homeTabAdapter

        val tabs: TabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabs, viewPager) {tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.cek_ongkir,
            R.string.cek_resi
        )
    }

}