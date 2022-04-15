package com.karyadika.cekongkir.ui.tabcekresi.trackingresult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karyadika.cekongkir.data.remote.response.ManifestItem
import com.karyadika.cekongkir.databinding.AdapterTrackingBinding

class TrackingResultAdapter(val manifests: List<ManifestItem>) : RecyclerView.Adapter<TrackingResultAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: AdapterTrackingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ManifestItem){
            binding.apply {
                textDate.text = "${data.manifestDate} ${data.manifestTime}"
                textDescription.text = data.manifestDescription
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterTrackingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(manifests[position])
    }

    override fun getItemCount() = manifests.size


}