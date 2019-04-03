package com.prilaga.news.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.prilaga.news.R
import com.prilaga.news.view.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        main_pager_container.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        main_pager_container.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // override desired callback functions
        })
        main_pager_container.adapter = MainPagerAdapter(supportFragmentManager)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_newspaper)
        supportActionBar?.let {
            it.setTitle(R.string.app_name)
            it.setSubtitle(R.string.subtitle)
        }
        toolbar.setOnClickListener {
            // ShareUtil.goToWeb(TextUtil.string(R.string.news_source_link))}
        }
    }

}