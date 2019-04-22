package com.prilaga.news.view.activity

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.prilaga.news.R
import com.prilaga.news.data.NewsRepository
import com.prilaga.news.view.adapter.MainPagerAdapter
import com.prilaga.news.view.adapter.TabItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.ext.android.inject

/**
 * Created by Oleg Tarashkevich on 28/03/2019.
 */
class MainActivity : AppCompatActivity() {

    private val viewModel by inject<NewsRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()

        news_tabs.setBackgroundColor(ContextCompat.getColor(this, R.color.primary))
        news_tabs.setSelectedTabIndicatorColor(Color.WHITE)
        news_tabs.setTabTextColors(ContextCompat.getColor(this, R.color.primary_light), Color.WHITE)

        main_pager_container.adapter = MainPagerAdapter(supportFragmentManager)
        news_tabs.setupWithViewPager(main_pager_container)

        // Change a page
        viewModel.articleParam.observe(
            this,
            Observer { main_pager_container.setCurrentItem(TabItem.ARTICLES.ordinal, true) })
        viewModel.sourceParam.observe(
            this,
            Observer { main_pager_container.setCurrentItem(TabItem.SOURCES.ordinal, true) })
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