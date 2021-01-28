package com.example.recyclertransition_dunets_l19

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclertransition_dunets_l19.databinding.DoggoRecyclerItemBinding

class DoggoRecyclerAdapter(private val mOnDoggoListener: OnDoggoListener) :
    RecyclerView.Adapter<DoggoRecyclerAdapter.DoggoViewHolder>() {

    private var items: List<Doggo> = emptyList()

    fun setItems(newItems: List<Doggo>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoggoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.doggo_recycler_item, parent, false)

        return DoggoViewHolder(view, mOnDoggoListener)
    }

    override fun onBindViewHolder(holder: DoggoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class DoggoViewHolder(itemView: View, private val onDoggoListener: OnDoggoListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val binding = DoggoRecyclerItemBinding.bind(itemView)
        private lateinit var doggo: Doggo

        fun bind(newDoggo: Doggo) {
            doggo = newDoggo
            binding.tvDoggoName.text = doggo.name
            Glide.with(itemView)
                .load(doggo.imgId)
                .centerCrop()
                .into(binding.ivDoggoIcon)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onDoggoListener.onDoggoClick(binding.tvDoggoName, binding.ivDoggoIcon, doggo)
        }
    }

    interface OnDoggoListener {
        fun onDoggoClick(tvName: TextView, ivIcon: ImageView, doggo: Doggo)
    }

}