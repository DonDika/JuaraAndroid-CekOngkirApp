package com.karyadika.cekongkir.ui.tabcekongkir.subdistrict

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.utils.Resource
import com.karyadika.cekongkir.data.remote.response.SubdistrictsItem
import com.karyadika.cekongkir.databinding.FragmentSubdistrictBinding
import com.karyadika.cekongkir.ui.tabcekongkir.city.CityFragment
import com.karyadika.cekongkir.ui.tabcekongkir.city.CityViewModel
import com.karyadika.cekongkir.ui.tabcekongkir.cost.CostFragment

class SubdistrictFragment : Fragment() {

    private lateinit var binding: FragmentSubdistrictBinding
    private lateinit var viewModel: CityViewModel
    private lateinit var subsdistrictAdapter: SubdistrictAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSubdistrictBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupView()
        setupRecyclerView()
        setupObserver()
        setupListener()

    }

    private fun setupListener() {
        val cityId = arguments?.getString(CityFragment.CITY_ID)
        binding.refreshSubdistrict.setOnRefreshListener {
            viewModel.fetchSubdistrict(cityId!!)
        }
    }

    private fun setupObserver() {
        viewModel.subdistrictResponse.observe(viewLifecycleOwner) {
            when(it) {
                is Resource.Loading -> binding.refreshSubdistrict.isRefreshing = true
                is Resource.Success -> {
                    binding.refreshSubdistrict.isRefreshing = false
                    subsdistrictAdapter.setData(it.data!!.rajaongkir.results)
                }
                is Resource.Error -> {
                    binding.refreshSubdistrict.isRefreshing = false
                }
            }
        }
    }

    private fun setupRecyclerView() {
        val type = requireActivity().intent.getStringExtra(CostFragment.INTENT_TYPE)
        val cityName = arguments?.getString(CityFragment.CITY_NAME)
        subsdistrictAdapter = SubdistrictAdapter(arrayListOf(), object : SubdistrictAdapter.OnAdapterListener{
            override fun onClick(results: SubdistrictsItem) {
                viewModel.savePreferences(
                    type!!, results.subdistrictId, "$cityName, ${results.subdistrictName}"
                )
                requireActivity().finish()
            }
        })
        binding.listSubdistrict.adapter = subsdistrictAdapter
    }

    private fun setupView() {
        viewModel.titleBar.postValue("Pilih Kecamatan")
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
    }

}