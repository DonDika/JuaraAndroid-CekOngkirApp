package com.karyadika.cekongkir.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.karyadika.cekongkir.ui.tabcekongkir.cost.CostFragment
import com.karyadika.cekongkir.ui.tabcekresi.waybill.WaybillFragment

class HomeTabAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = CostFragment()
            1 -> fragment = WaybillFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

}