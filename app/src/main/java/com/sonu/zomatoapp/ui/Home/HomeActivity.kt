package com.sonu.zomatoapp.ui.Home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sonu.zomatoapp.R
import com.sonu.zomatoapp.adapter.RestaurantsAdapter
import com.sonu.zomatoapp.ui.Search.SearchActivity
import com.sonu.zomatoapp.ui.viewmodels.RestaurantModelFactory
import com.sonu.zomatoapp.ui.viewmodels.RestaurantViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {
    var mFusedLocationClient: FusedLocationProviderClient? = null
    override val kodein by kodein()
    private val factory: RestaurantModelFactory by instance()

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val viewModel = ViewModelProvider(this, factory).get(RestaurantViewModel::class.java)
        mFusedLocationClient!!.lastLocation.addOnSuccessListener {
            viewModel.getRestaurants(it.latitude, it.longitude)
        }
        viewModel.restaurants.observe(this, {
            rest_recycleView.setHasFixedSize(true)
            rest_recycleView.adapter = it.nearby_restaurants?.let { it1 -> RestaurantsAdapter(it1) }
        })

        search.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

    }


}