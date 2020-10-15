package com.sonu.zomatoapp

import android.app.Application
import com.sonu.zomatoapp.data.network.MyApi
import com.sonu.zomatoapp.data.network.NetworkConnectionInterceptor
import com.sonu.zomatoapp.data.repositories.RestaurantsRepository
import com.sonu.zomatoapp.ui.viewmodels.RestaurantModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ZomatoApp : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ZomatoApp))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { RestaurantsRepository(instance()) }
        bind() from provider { RestaurantModelFactory(instance()) }

    }

}