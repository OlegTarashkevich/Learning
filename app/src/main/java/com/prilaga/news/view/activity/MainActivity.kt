package com.prilaga.news.view.activity

import android.os.Bundle
import android.widget.Toast
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

        lifecycle.addObserver(sourceViewModel)

        main_pager_container

//        test_button.setOnClickListener { sourceViewModel.loadNews() }

        sourceViewModel.sourceData.observe(this, Observer<Source> {

        })

        sourceViewModel.errorData.observe(this, Observer<Throwable>{
            it.printStackTrace()
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

}