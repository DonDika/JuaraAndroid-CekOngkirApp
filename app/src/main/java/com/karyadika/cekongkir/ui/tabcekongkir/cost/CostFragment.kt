package com.karyadika.cekongkir.ui.tabcekongkir.cost

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.karyadika.cekongkir.utils.Resource
import com.karyadika.cekongkir.databinding.FragmentCostBinding
import com.karyadika.cekongkir.ui.tabcekongkir.city.CityActivity
import timber.log.Timber

class CostFragment : Fragment() {

    private lateinit var binding: FragmentCostBinding
    private lateinit var viewModel: CostViewModel

    private var originId: String? = ""
    private var destinationId: String? = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupListener()
        setupObserver()

    }


    override fun onStart() {
        super.onStart()

        viewModel.getPreferences()
        loading(false)
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(CostViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.preferences.observe(viewLifecycleOwner){ preferencesList ->
            preferencesList.forEach {
                when(it.type) {
                    "origin" -> {
                        originId = it.id
                        binding.editOrigin.setText(it.name)
                    }
                    "destination" -> {
                        destinationId = it.id
                        binding.editDestination.setText(it.name)
                    }
                }
            }
        }
        viewModel.costResponse.observe(viewLifecycleOwner){
            when(it) {
                is Resource.Loading -> {
                    Timber.e("loading")
                    loading(true)
                }
                is Resource.Success -> {
                    loading(false)
                    binding.listCost.adapter = CostAdapter(it.data!!.costRajaOngkir.results)
                }
                is Resource.Error -> {
                    loading(false)
                }
            }
        }

    }


    private fun setupListener() {
        binding.editOrigin.setOnClickListener {
            startActivity(Intent(context, CityActivity::class.java).putExtra(INTENT_TYPE, "origin"))
        }
        binding.editDestination.setOnClickListener {
            val intent = Intent(context, CityActivity::class.java)
            intent.putExtra(INTENT_TYPE, "destination")
            startActivity(intent)
        }
        binding.buttonCost.setOnClickListener {
            if (originId.isNullOrEmpty() || destinationId.isNullOrEmpty()) {
                Toast.makeText(requireActivity(), "Lengkapi data pencarian", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.fetchCost(originId!!, "subdistrict", destinationId!!, "subdistrict", "1000", "sicepat:jnt:pos" )
            }
        }
    }


    private fun loading(isLoading: Boolean){
        if(isLoading) binding.progressCost.visibility = View.VISIBLE
        else binding.progressCost.visibility = View.GONE
    }

    companion object {
        const val INTENT_TYPE = "intent_type"
    }


}