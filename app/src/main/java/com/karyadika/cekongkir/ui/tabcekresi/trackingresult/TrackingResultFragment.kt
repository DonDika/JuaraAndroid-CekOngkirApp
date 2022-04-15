package com.karyadika.cekongkir.ui.tabcekresi.trackingresult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.utils.Resource
import com.karyadika.cekongkir.databinding.FragmentTrackingResultBinding
import com.karyadika.cekongkir.ui.tabcekresi.tracking.TrackingFragment
import com.karyadika.cekongkir.ui.tabcekresi.tracking.TrackingViewModel

class TrackingResultFragment : Fragment() {

    private lateinit var binding: FragmentTrackingResultBinding
    private lateinit var viewModel: TrackingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTrackingResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupObserver()
    }

    override fun onStart() {
        super.onStart()
        val waybill = arguments?.getString(TrackingFragment.WAYBILL)
        val courier = arguments?.getString(TrackingFragment.COURIER)
        viewModel.fetchWaybill(waybill!!, courier!!)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(TrackingViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.waybillResponse.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    loading(true)
                }
                is Resource.Success -> {
                    loading(false)
                    val data = it.data!!.waybillRajaOngkir.result
                    binding.apply {
                        textStatus.text = data.deliveryStatus.status
                        textReceiver.text = data.deliveryStatus.podReceiver
                        textDate.text = "${data.deliveryStatus.podDate} ${data.deliveryStatus.podTime}"
                        listManifest.adapter = TrackingResultAdapter(data.manifest)
                    }
                }
                is Resource.Error -> {
                    loading(false)
                    Toast.makeText(context, "Resi tidak ditemukan!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loading(isLoading: Boolean){
        if(isLoading){
            binding.refreshWaybill.isRefreshing = true
            binding.container.visibility = View.GONE
        } else {
            binding.refreshWaybill.isRefreshing = false
            binding.container.visibility = View.VISIBLE
        }
    }

}