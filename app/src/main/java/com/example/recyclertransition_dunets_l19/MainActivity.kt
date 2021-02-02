package com.example.recyclertransition_dunets_l19

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.toAndroidPair
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclertransition_dunets_l19.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val dogsList = listOf(
            Doggo("Dog One", 2, R.drawable.doggo1),
            Doggo("Dog Two", 1, R.drawable.doggo2),
            Doggo("Dog Three", 3, R.drawable.doggo3),
            Doggo("Dog Four", 2, R.drawable.doggo4),
            Doggo("Dog Five", 1, R.drawable.doggo5),
            Doggo("Dog Six", 3, R.drawable.doggo6),
            Doggo("Dog Seven", 2, R.drawable.doggo7),
            Doggo("Dog Eight", 1, R.drawable.doggo8)
        )
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var doggoAdapter: DoggoRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupRecycler()
    }

    private fun setupRecycler() {
        doggoAdapter = DoggoRecyclerAdapter { tvName, ivIcon, doggo ->

            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(DOGGO_INFO_TAG, doggo)
            }
            val pairImage =
                Pair<View, String>(ivIcon, getString(R.string.icon_transition)).toAndroidPair()
            val pairName =
                Pair<View, String>(tvName, getString(R.string.name_transition)).toAndroidPair()

            val activityOptions = ActivityOptions
                .makeSceneTransitionAnimation(this, pairImage, pairName)

            startActivity(intent, activityOptions.toBundle())
        }
        doggoAdapter.setItems(dogsList)

        binding.rvMain.apply {
            adapter = doggoAdapter

            layoutManager = LinearLayoutManager(
                this@MainActivity,
                RecyclerView.VERTICAL,
                false
            )
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
            )
        }
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}