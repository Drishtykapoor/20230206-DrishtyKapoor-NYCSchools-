package com.jpmc.a20230130drishtykapoornycschools.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jpmc.a20230130drishtykapoornycschools.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Provider


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navControllerProvider: Provider<NavController>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        val appBarConfiguration = AppBarConfiguration(navControllerProvider.get().graph)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        findViewById<Toolbar>(R.id.my_toolbar)
            .setupWithNavController(navControllerProvider.get(), appBarConfiguration)
    }
}

