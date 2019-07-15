package com.cpu.quikdata.feature.consortium

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import com.cpu.quikdata.R
import com.cpu.quikdata.common.clickWithGuard
import com.cpu.quikdata.common.setupClipping
import kotlinx.android.synthetic.main.activity_consortium.*

class ConsortiumActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, ConsortiumActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consortium)

        setupClipping(consortiumFragmentLayout)

        setSupportActionBar(consortiumToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        consortiumPlanButton.addWebLink("https://plan-international.org/")
        consortiumCdacButton.addWebLink("http://www.cdacnetwork.org/")
        consortiumActionButton.addWebLink("https://www.actionagainsthunger.org/")
        consortiumCdrcButton.addWebLink("https://www.cdrc-phil.com/")
        consortiumStartButton.addWebLink("https://startnetwork.org/")
        consortiumCareButton.addWebLink("https://www.care-international.org/")
        consortiumUkaidButton.addWebLink("https://www.ukaiddirect.org/")
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun ImageView.addWebLink(url: String) {
        this.clickWithGuard {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}
