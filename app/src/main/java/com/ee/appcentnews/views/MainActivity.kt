package com.ee.appcentnews.views

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ee.appcentnews.R
import com.ee.appcentnews.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Setup BottomNav
        val navController = findNavController(this, R.id.fragmentContainerView)
        binding.bottomNav.setupWithNavController(navController)

        //BottomNav visibility settings
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.contentFragment, R.id.newSourceFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                else -> {
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }


    }

}