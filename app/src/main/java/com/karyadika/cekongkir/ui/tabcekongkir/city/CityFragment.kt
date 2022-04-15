package com.karyadika.cekongkir.ui.tabcekongkir.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.karyadika.cekongkir.R
import com.karyadika.cekongkir.utils.Resource
import com.karyadika.cekongkir.data.remote.response.CitiesItem
import com.karyadika.cekongkir.databinding.FragmentCityBinding

class CityFragment : Fragment() {

    private lateinit var binding: FragmentCityBinding
    private lateinit var viewModel: CityViewModel
    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupView()
        setupObserver()
        setupRecyclerView()
        setupListener()

    }

    private fun setupView() {
        viewModel.titleBar.postValue("Pilih Kota")
    }

    private fun setupListener() {
        binding.editSearch.doAfterTextChanged { input ->
            cityAdapter.filter.filter(input.toString())
        }
        binding.refreshCity.setOnRefreshListener {
            viewModel.fetchCity()
        }

    }

    private fun setupRecyclerView() {
        cityAdapter = CityAdapter(arrayListOf(), object : CityAdapter.OnAdapterListener{
            override fun onClick(results: CitiesItem) {
                val mBundle = Bundle()
                mBundle.putString(  CITY_ID, results.cityId)
                mBundle.putString(CITY_NAME, results.cityName)
                findNavController().navigate(R.id.action_cityFragment_to_subdistrictFragment, mBundle)
                viewModel.fetchSubdistrict(results.cityId)
            }
        })
        binding.listCity.adapter = cityAdapter

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(CityViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.cityResponse.observe(viewLifecycleOwner){
            when(it) {
                is Resource.Loading -> binding.refreshCity.isRefreshing = true
                is Resource.Success -> {
                    binding.refreshCity.isRefreshing = false
                    cityAdapter.setData(it.data!!.rajaongkir.results)
                }
                is Resource.Error -> {
                    binding.refreshCity.isRefreshing = false
                }
            }
        }

    }

    companion object {
        const val CITY_ID = "city_id"
        const val CITY_NAME = "city_name"
    }

}