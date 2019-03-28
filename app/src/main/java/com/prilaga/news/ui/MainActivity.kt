package com.prilaga.news.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Source
import com.prilaga.news.viewmodel.SourceViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class MainActivity : AppCompatActivity() {

    private val sourceViewModel: SourceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test_button.setOnClickListener { sourceViewModel.loadNews() }

        sourceViewModel.sourceData.observe(this, Observer<Source> {

        })
    }

}