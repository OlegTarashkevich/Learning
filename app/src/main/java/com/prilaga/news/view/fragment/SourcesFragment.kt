package com.prilaga.news.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prilaga.data.utils.Logger
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Source
import com.prilaga.news.view.adapter.SourceRecyclerAdapter
import com.prilaga.news.viewmodel.SourceViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

class SourcesFragment : BaseFragment() {

    private val sourceViewModel: SourceViewModel by viewModel()

    internal var adapter = SourceRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        page_recycler_view!!.layoutManager = LinearLayoutManager(context)
        page_recycler_view!!.adapter = adapter

//        For testing
//        onDataLoadEvent(Source.createParam());
//        onDataLoadEvent(Source.createParam(RequestParam.Category.BUSINESS, RequestParam.Language.EN, RequestParam.Country.US));

        lifecycle.addObserver(sourceViewModel)   // Receive the source data
        sourceViewModel.sourceData.observe(this, Observer {
            Logger.separator()
            updateRecycleView(it)
        })

        // Receive the error
        sourceViewModel.errorData.observe(this, Observer {
            it.printStackTrace()
//            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    // region INewsView
    fun onDataLoadEvent(param: Source.Param) {
        adapter.clear()
//        sourcePresenter!!.loadData(createParam)
    }

    fun onStartLoading() {
        showProgress(true)
    }

    fun onFailure(message: String) {
        showProgress(false)
    }

    fun updateRecycleView(source: Source) {
        showProgress(false)
        adapter.setData(source.sources)
    }
    // endregion
}
