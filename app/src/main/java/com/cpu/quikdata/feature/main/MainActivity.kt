package com.cpu.quikdata.feature.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cpu.quikdata.R
import com.cpu.quikdata.common.setupClipping
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbarTitle

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            context.startActivity(intent)
        }
    }

    private lateinit var mNavController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupClipping(mainActivityFragmentLayout)
        mNavController = findNavController(R.id.mainActivityFragment)
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            toolbarTitle.text = destination.label
        }
        mainActivityNavigationView.setupWithNavController(mNavController)

}

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
