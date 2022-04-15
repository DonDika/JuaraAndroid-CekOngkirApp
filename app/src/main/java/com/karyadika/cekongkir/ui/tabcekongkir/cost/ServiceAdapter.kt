package com.karyadika.cekongkir.ui.tabcekongkir.cost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karyadika.cekongkir.data.remote.response.CostsItem
import com.karyadika.cekongkir.databinding.AdapterServiceBinding

class ServiceAdapter(val costs: List<CostsItem> ) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(costs[position])
    }

    override fun getItemCount() = costs.size

    inner class ViewHolder(val binding: AdapterServiceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(costs: CostsItem) {
            binding.apply {
                textService.text = costs.service
                textDescription.text = costs.description
                textValue.text = costs.cost[0].value.toString()
                textEtd.text = costs.cost[0].etd
            }
        }
    }

}