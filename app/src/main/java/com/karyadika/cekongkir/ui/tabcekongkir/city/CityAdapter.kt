package com.karyadika.cekongkir.ui.tabcekongkir.city

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.karyadika.cekongkir.data.remote.response.CitiesItem
import com.karyadika.cekongkir.databinding.AdapterCityBinding
import timber.log.Timber

class CityAdapter(var cities: ArrayList<CitiesItem>, var listener: OnAdapterListener) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>(), Filterable {

    private var citiesFilter = ArrayList<CitiesItem>()

    init {
        citiesFilter = cities
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(citiesFilter[position])
    }

    override fun getItemCount() = citiesFilter.size


    inner class ViewHolder(var binding: AdapterCityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(city: CitiesItem) {
            binding.textName.text = city.cityName
            binding.container.setOnClickListener {
                listener.onClick(city)
            }
        }
    }

    interface OnAdapterListener {
        fun onClick(results: CitiesItem)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("charSearch: $charSearch")
                if (charSearch.isEmpty()) {
                    citiesFilter = cities
                } else {
                    val citiesFiltered = ArrayList<CitiesItem>()
                    for (city in cities) {
                        if (city.cityName.toLowerCase().contains(charSearch.toLowerCase())) {
                            citiesFiltered.add(city)
                        }
                    }
                    citiesFilter = citiesFiltered
                }
                val citiesFilteredResult = FilterResults()
                citiesFilteredResult.values = citiesFilter
                return citiesFilteredResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                citiesFilter = results?.values as ArrayList<CitiesItem>
                notifyDataSetChanged()
            }

        }
    }

    fun setData(data: List<CitiesItem>) {
        cities.clear()
        cities.addAll(data)
        notifyDataSetChanged()
    }



}