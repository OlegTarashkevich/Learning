package com.prilaga.news.view.widget

import android.app.Activity
import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.cardview.widget.CardView
import com.prilaga.news.R
import com.prilaga.news.data.network.API
import com.prilaga.news.util.ViewUtil
import com.prilaga.news.view.adapter.RequestAdapter
import kotlinx.android.synthetic.main.cardview_request.view.*


/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

class RequestCardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CardView(context, attrs, defStyleAttr) {

    val category: String get() = category_edit_text!!.editableText.toString()
    val language: String get() = language_edit_text!!.editableText.toString()
    val country: String get() = country_edit_text!!.editableText.toString()

    init {
        View.inflate(context, R.layout.cardview_request, this)
    }

    fun setApi(api: API) {
        presenter.changeApi(api)
    }

    fun refreshNews() {
        presenter.refreshNews()
    }

    // region IRequestCardView
    fun setCategory(categories: Array<String>, category: String) {
        setupFiled(category_edit_text!!, categories, category)
    }

    fun setLanguage(languages: Array<String>, language: String) {
        setupFiled(language_edit_text!!, languages, language)
    }

    fun setCountries(countries: Array<String>, country: String) {
        setupFiled(country_edit_text!!, countries, country)
    }
    // endregion

    private fun setupFiled(editText: AppCompatAutoCompleteTextView, array: Array<String>, value: String) {
        val categoryAdapter = RequestAdapter(array)
        editText.setAdapter(categoryAdapter)
        editText.setText(value)
        editText.inputType = InputType.TYPE_NULL

        editText.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val item = (parent.adapter as RequestAdapter).getItem(position)
            editText.setText(item)
            if (presenter != null)
                presenter.startRequestParam()
        }

        editText.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action != MotionEvent.ACTION_UP)
                return@OnTouchListener false

            ViewUtil.hideKeyboard(editText)
            editText.showDropDown()
            false
        })
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (presenter != null)
            presenter.loadRequestParam()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (presenter != null)
            presenter.unSubscribe()
    }
}