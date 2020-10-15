package com.sonu.zomatoapp.ui.Search

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sonu.zomatoapp.R
import com.sonu.zomatoapp.adapter.RestaurantsAdapter
import com.sonu.zomatoapp.ui.viewmodels.RestaurantModelFactory
import com.sonu.zomatoapp.ui.viewmodels.RestaurantViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_search.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SearchActivity : AppCompatActivity(), KodeinAware {
    var mFusedLocationClient: FusedLocationProviderClient? = null
    override val kodein by kodein()
    private val factory: RestaurantModelFactory by instance()
    var latitude: Double? = null
    var longitude: Double? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val viewModel = ViewModelProvider(this, factory).get(RestaurantViewModel::class.java)
        mFusedLocationClient!!.lastLocation.addOnSuccessListener {
            if (it != null) {
                latitude = it.latitude
                longitude = it.longitude
            }
        }
        viewModel.searchedrestaurants.observe(this, {
            search_rest_recycleView.setHasFixedSize(true)
            search_rest_recycleView.adapter = it.restaurants?.let { it1 -> RestaurantsAdapter(it1) }
            search_progressbar.visibility = View.INVISIBLE
        })

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.search_toolbar)
        setSupportActionBar(toolbar!!)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search_progressbar.visibility = View.VISIBLE
                viewModel.getSearchedRestaurants(latitude!!, longitude!!, s.toString())

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}