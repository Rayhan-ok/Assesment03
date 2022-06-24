package org.d3if0031.assesment03.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0031.assesment03.model.Makanan
import org.d3if0031.assesment03.R
import org.d3if0031.assesment03.databinding.ListItemBinding
import org.d3if0031.assesment03.network.MakananApi


class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val data = mutableListOf<Makanan>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Makanan>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(makanan: Makanan) = with(binding) {
            namaTextView.text = makanan.nama
            latinTextView.text = makanan.harga
            println("iasa = " + MakananApi.getMakananUrl(makanan.imageId))
            Glide.with(imageView.context)
                .load(MakananApi.getMakananUrl(makanan.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, makanan.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}