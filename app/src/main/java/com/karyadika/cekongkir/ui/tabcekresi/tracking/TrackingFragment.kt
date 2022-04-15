package com.karyadika.cekongkir.ui.tabcekresi.tracking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.karyadika.cekongkir.R
import com.karyadika.cekongkir.databinding.FragmentTrackingBinding
import com.karyadika.cekongkir.ui.tabcekresi.waybill.WaybillFragment

class TrackingFragment : Fragment() {

    private lateinit var binding: FragmentTrackingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTrackingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }

    private fun setupListener() {
        val isTracking = requireActivity().intent.getBooleanExtra(WaybillFragment.IS_TRACKING, false)
        if (isTracking){
            val waybill = requireActivity().intent.getStringExtra(WaybillFragment.IS_WAYBILL)
            val courier = requireActivity().intent.getStringExtra(WaybillFragment.IS_COURIER)
            val mBundle = Bundle()
            mBundle.putString(WAYBILL, waybill)
            mBundle.putString(COURIER, courier)
            findNavController().navigate(R.id.action_trackingFragment_to_trackingResultFragment, mBundle)
        }
        val courierAdapter = ArrayAdapter.createFromResource(requireContext(), R.array.courier, android.R.layout.simple_spinner_item)
        courierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.listCourier.adapter = courierAdapter

        binding.buttonTrack.setOnClickListener {
            val mBundle = Bundle()
            mBundle.putString(WAYBILL, binding.editWaybill.text.toString())
            mBundle.putString(COURIER, binding.listCourier.selectedItem.toString())
            findNavController().navigate(R.id.action_trackingFragment_to_trackingResultFragment, mBundle)
        }
    }

    companion object {
        const val WAYBILL = "waybill"
        const val COURIER = "courier"
    }

}