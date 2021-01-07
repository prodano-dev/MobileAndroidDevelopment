package com.example.madlevel2task1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.madlevel2task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val places = arrayListOf<Place>()
    private val placeAdapter = PlaceAdapter(places)
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {


        binding.ivPlaces.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.ivPlaces.adapter = placeAdapter

        for (i in Place.PLACE_NAMES.indices) {

            places.add(Place(Place.PLACE_NAMES[i],
                Place.PLACE_RES_DRAWABLE_IDS[i])
            )
        }

        placeAdapter.notifyDataSetChanged()
    }

}