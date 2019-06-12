package com.cpu.quikdata.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupClipping
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupClipping(mainActivityFragmentLayout)

        mNavController = findNavController(R.id.mainActivityFragment)
        mainActivityNavigationView.setupWithNavController(mNavController)

        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            toolbarTitle.text = destination.label
        }
    }
}
