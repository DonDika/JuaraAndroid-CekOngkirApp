package com.karyadika.cekongkir.ui.tabcekresi.waybill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karyadika.cekongkir.data.local.persistence.WaybillEntity
import com.karyadika.cekongkir.databinding.AdapterWaybillBinding

class WaybillAdapter(val waybills: ArrayList<WaybillEntity>, val listener: OnAdapterListener) : RecyclerView.Adapter<WaybillAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterWaybillBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(waybill: WaybillEntity){
            binding.apply {
                textWaybill.text = waybill.waybill
                textCourier.text = waybill.courier
                textStatus.text = waybill.status
                container.setOnClickListener {
                    listener.onDetail(waybill)
                }
                container.setOnLongClickListener {
                    listener.onDetele(waybill)
                    true
                }
            }
        }
    }

    interface OnAdapterListener {
        fun onDetail(result: WaybillEntity)
        fun onDetele(result: WaybillEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterWaybillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(waybills[position])
    }

    override fun getItemCount() = waybills.size

    fun setData(data: List<WaybillEntity>){
        waybills.clear()
        waybills.addAll(data)
        notifyDataSetChanged()
    }

}