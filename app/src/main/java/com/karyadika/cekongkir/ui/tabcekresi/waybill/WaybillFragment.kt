package com.karyadika.cekongkir.ui.tabcekresi.waybill

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.data.local.persistence.WaybillEntity
import com.karyadika.cekongkir.databinding.FragmentWaybillBinding
import com.karyadika.cekongkir.ui.tabcekresi.tracking.TrackingActivity
import com.karyadika.cekongkir.ui.tabcekresi.tracking.TrackingViewModel


class WaybillFragment : Fragment() {

    private lateinit var binding: FragmentWaybillBinding
    private lateinit var viewModel: TrackingViewModel
    private lateinit var waybillAdapter: WaybillAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWaybillBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupRecyclerView()
        setupListener()
        setupObserver()
    }

    private fun setupRecyclerView() {
        waybillAdapter = WaybillAdapter(arrayListOf(), object : WaybillAdapter.OnAdapterListener{
            override fun onDetail(result: WaybillEntity) {
                val toDetailResi = Intent(requireActivity(), TrackingActivity::class.java)
                toDetailResi.apply {
                    putExtra(IS_TRACKING, true)
                    putExtra(IS_WAYBILL, result.waybill)
                    putExtra(IS_COURIER, result.courier)
                }
                startActivity(toDetailResi)
            }
            override fun onDetele(result: WaybillEntity) {
                val builder = AlertDialog.Builder(requireActivity())
                builder.apply {
                    setTitle("Hapus Resi")
                    setMessage("Hapus No. Resi ${result.waybill}?")
                    setPositiveButton("Hapus"){ _, _->
                        viewModel.  deleteWaybill(result)
                        Toast.makeText(requireActivity(), "Resi dihapus", Toast.LENGTH_SHORT).show()
                    }
                    setNegativeButton("Batal"){ _, _->
                    }
                    show()
                }
            }
        })
        binding.listWaybill.adapter = waybillAdapter
    }

    private fun setupObserver() {
        viewModel.getWaybill.observe(viewLifecycleOwner, Observer {
            waybillAdapter.setData(it)
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java)
    }

    private fun setupListener() {
        binding.editWaybill.setOnClickListener {
            val intent = Intent(requireActivity(), TrackingActivity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val IS_TRACKING = "is_tracking"
        const val IS_WAYBILL = "is_waybill"
        const val IS_COURIER = "is_courier"
    }

}