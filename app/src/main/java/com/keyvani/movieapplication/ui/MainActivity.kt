package com.keyvani.movieapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.keyvani.movieapplication.R
import com.keyvani.movieapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Binding
    private lateinit var binding: ActivityMainBinding
    //Other
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initViews
        binding.apply {
            navController =findNavController(R.id.navHost)
            bottomNav.setupWithNavController(navController)
            //Show bottom navigation
            navController.addOnDestinationChangedListener{_,destination,_ ->
                if(destination.id == R.id.splashFragment || destination.id == R.id.registerFragment || destination.id == R.id.detailFragment){
                    bottomNav.visibility= View.GONE
                }else{
                    bottomNav.visibility= View.VISIBLE
                }
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}