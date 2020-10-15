package com.sonu.zomatoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sonu.zomatoapp.R
import com.sonu.zomatoapp.data.model.NearbyRestaurant

class RestaurantsAdapter(val restaurantsList: ArrayList<NearbyRestaurant>) :

    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.single_restaurant, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(restaurantsList[position])
    }

    override fun getItemCount(): Int {
        return restaurantsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.rest_image);
        var title = itemView.findViewById<TextView>(R.id.rest_name);

        fun bindItems(restaurant: NearbyRestaurant) {
            Glide.with(itemView.context)
                .load(restaurant.restaurant!!.featured_image)
                .error(R.drawable.ic_launcher_background)
                .into(image)
            title.setText(restaurant.restaurant!!.name)
        }
    }
}
