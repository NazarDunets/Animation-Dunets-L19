package com.example.recyclertransition_dunets_l19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclertransition_dunets_l19.databinding.DoggoRecyclerItemBinding

class DoggoRecyclerAdapter(private val bloc: (TextView, ImageView, Doggo) -> Unit) :
    RecyclerView.Adapter<DoggoRecyclerAdapter.DoggoViewHolder>() {

    private var items: List<Doggo> = emptyList()

    fun setItems(newItems: List<Doggo>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoggoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.doggo_recycler_item, parent, false)

        return DoggoViewHolder(view, bloc)
    }

    override fun onBindViewHolder(holder: DoggoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class DoggoViewHolder(itemView: View, private val bloc: (TextView, ImageView, Doggo) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val binding = DoggoRecyclerItemBinding.bind(itemView)

        fun bind(newDoggo: Doggo) {
            binding.tvDoggoName.text = newDoggo.name
            Glide.with(itemView)
                .load(newDoggo.imgId)
                .centerCrop()
                .into(binding.ivDoggoIcon)

            itemView.setOnClickListener {
                bloc(binding.tvDoggoName, binding.ivDoggoIcon, newDoggo)
            }
        }

    }

}