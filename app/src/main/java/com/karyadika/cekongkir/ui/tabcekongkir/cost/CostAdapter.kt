package com.karyadika.cekongkir.ui.tabcekongkir.cost

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karyadika.cekongkir.data.remote.response.ResultsItem
import com.karyadika.cekongkir.databinding.AdapterCostBinding

class CostAdapter(val costs: List<ResultsItem> ) : RecyclerView.Adapter<CostAdapter.ViewHoler>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoler {
        val binding = AdapterCostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHoler(binding)
    }

    override fun onBindViewHolder(holder: ViewHoler, position: Int) {
        holder.bind(costs[position])
    }

    override fun getItemCount(): Int {
        return costs.size
    }

    inner class ViewHoler(val binding: AdapterCostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cost: ResultsItem) {
            binding.apply {
                textCode.text = cost.code
                textCode.text = cost.name
                listService.adapter = ServiceAdapter(cost.costs)
            }
        }
    }


}