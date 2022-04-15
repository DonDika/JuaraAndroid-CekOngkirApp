package com.karyadika.cekongkir.ui.tabcekongkir.subdistrict

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karyadika.cekongkir.data.remote.response.SubdistrictsItem
import com.karyadika.cekongkir.databinding.AdapterSubdistrictBinding

class SubdistrictAdapter(
    val subdistricts: ArrayList<SubdistrictsItem>,
    val listener: OnAdapterListener) :
    RecyclerView.Adapter<SubdistrictAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterSubdistrictBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(subdistricts[position])
    }

    override fun getItemCount() = subdistricts.size

    inner class ViewHolder(var binding: AdapterSubdistrictBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subdistricts: SubdistrictsItem) {
            binding.textName.text = subdistricts.subdistrictName
            binding.container.setOnClickListener {
                listener.onClick(subdistricts)
            }
        }
    }

    interface OnAdapterListener {
        fun onClick(results: SubdistrictsItem)
    }

    fun setData(data: List<SubdistrictsItem>) {
        subdistricts.clear()
        subdistricts.addAll(data)
        notifyDataSetChanged()
    }
}