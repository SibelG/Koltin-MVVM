package com.app.koltinpoc.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.app.koltinpoc.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlin.concurrent.fixedRateTimer

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navigationHost: NavHostFragment
    lateinit var bottomNav : BottomNavigationView
    lateinit var bottomNavView : NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        bottomNavView = navigationHost.navController
        bottomNav = findViewById(R.id.bottom_nav_view)
        //bottomNavView=findNavController(R.id.nav_host_fragment_container)

        bottomNav.setupWithNavController(bottomNavView)

        /*appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.breakingNewsFragment,
                R.id.savedNewsFragment

            )
        )*/
        //setupActionBarWithNavController(bottomNavView, appBarConfiguration)


        /*override fun onSupportNavigateUp(): Boolean {
        return bottomNavView.navigateUp(appBarConfiguration)
    }*/
    }
}