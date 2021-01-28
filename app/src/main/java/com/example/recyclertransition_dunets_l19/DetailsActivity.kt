package com.example.recyclertransition_dunets_l19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recyclertransition_dunets_l19.databinding.DetailsActivityBinding

const val DOGGO_INFO_TAG = "DOGGO_INFO_TAG"

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: DetailsActivityBinding

    private lateinit var doggo: Doggo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        doggo = intent?.extras?.getParcelable(DOGGO_INFO_TAG) ?: Doggo(
            "Name",
            0,
            R.color.purple_200
        )

        setupBinding()
        setupViews()
    }

    private fun setupBinding() {
        binding = DetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViews() {
        binding.tvDetailsName.text = doggo.name
        binding.tvDetailsAge.text = getString(R.string.details_age, doggo.age)

        Glide.with(this)
            .load(doggo.imgId)
            .centerCrop()
            .into(binding.ivDetailsIcon)
    }

}