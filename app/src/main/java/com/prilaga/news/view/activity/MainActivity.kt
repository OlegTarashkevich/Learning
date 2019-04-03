package com.prilaga.news.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Source
import com.prilaga.news.viewmodel.SourceViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class MainActivity : AppCompatActivity() {

    private val sourceViewModel: SourceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        
        lifecycle.addObserver(sourceViewModel)

        main_pager_container

//        test_button.setOnClickListener {  }

        // Receive the source data
        sourceViewModel.sourceData.observe(this, Observer<Source> {

        })

        // Receive the error
        sourceViewModel.errorData.observe(this, Observer<Throwable> {
            it.printStackTrace()
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_newspaper)
        supportActionBar?.let {
            it.setTitle(R.string.app_name)
            it.setSubtitle(R.string.subtitle)
        }
        toolbar.setOnClickListener(View.OnClickListener {
//            ShareUtil.goToWeb(TextUtil.string(R.string.news_source_link))
        })
    }

    override fun onDestroy() {
        lifecycle.removeObserver(sourceViewModel)
        super.onDestroy()
    }
}