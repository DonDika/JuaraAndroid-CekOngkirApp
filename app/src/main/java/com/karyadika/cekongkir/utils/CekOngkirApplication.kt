package com.karyadika.cekongkir.utils

import android.app.Application
import com.karyadika.cekongkir.data.CekOngkirRepository
import com.karyadika.cekongkir.data.local.persistence.CekOngkirDatabase
import com.karyadika.cekongkir.data.local.preferences.CekOngkirPreference
import com.karyadika.cekongkir.data.remote.retrofit.ApiConfig
import com.karyadika.cekongkir.data.remote.retrofit.ApiService
import com.karyadika.cekongkir.ui.tabcekongkir.city.CityViewModelFactory
import com.karyadika.cekongkir.ui.tabcekongkir.cost.CostViewModelFactory
import com.karyadika.cekongkir.ui.tabcekresi.tracking.TrackingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import timber.log.Timber

class CekOngkirApplication : Application(), KodeinAware {

    //Timber
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }

    //DI Kodein
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@CekOngkirApplication))

        //api
        bind<ApiService>() with singleton {
            ApiConfig.getClient()
        }

        //pref
        bind() from singleton {
            CekOngkirPreference( instance() )
        }

        //room
        bind() from singleton {
            CekOngkirDatabase( instance() )
        }

        //repo
        bind() from singleton {
            CekOngkirRepository( instance(), instance(), instance() )
        }

        //ViewModelFactory
        bind() from singleton {
            CityViewModelFactory( instance() )
        }

        bind() from singleton {
            CostViewModelFactory( instance() )
        }

        bind() from singleton {
            TrackingViewModelFactory( instance() )
        }

    }

}