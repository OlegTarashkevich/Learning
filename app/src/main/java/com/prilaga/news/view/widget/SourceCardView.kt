package com.prilaga.news.view.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.prilaga.data.utils.ListUtil
import com.prilaga.data.utils.Logger
import com.prilaga.news.R
import com.prilaga.news.data.network.model.RequestParam
import com.prilaga.news.data.network.model.Source
import com.prilaga.news.viewmodel.RequestViewModel
import kotlinx.android.synthetic.main.cardview_source.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

class SourceCardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CardView(context, attrs, defStyleAttr), View.OnClickListener, LifecycleOwner, KoinComponent {

    private val mLifecycleRegistry = LifecycleRegistry(this)
    private val requestViewModel: RequestViewModel by inject()
    private val primaryLight: Int get() = ContextCompat.getColor(context, R.color.primary_light)
    private val options: RequestOptions by lazy {
        val logoSize: Int = context.resources.getDimension(R.dimen.z_size).toInt()
        RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_newspaper)
            .override(logoSize, logoSize)
            .priority(Priority.HIGH)
    }

    private var mEntry: Source.Entry? = null
    var cardSelection: CardSelection? = null

    init {
        mLifecycleRegistry.addObserver(requestViewModel)
        inflate(context, R.layout.cardview_source, this)
    }

    fun setSource(entry: Source.Entry?) {
        mEntry = entry
        if (entry == null) {
            setOnClickListener(null)
            visibility = View.INVISIBLE
        } else {
            setOnClickListener(this)
            visibility = View.VISIBLE

            source_name_text_view!!.text = entry.info
            source_description_text_view!!.text = entry.description
            source_url_text_view!!.text = entry.url

            try {
                Glide.with(context)
//                    .applyDefaultRequestOptions(options)
                    .load(entry.urlsToLogos!!.medium)
                    .apply(options)
                    .into(source_logo_view)

//                picasso!!.load(TextUtil.getEmptyUrl(entry.urlsToLogos!!.medium))
//                    .placeholder(R.drawable.ic_newspaper)
//                    .error(R.drawable.ic_newspaper)
//                    .centerInside()
//                    .resize(logoSize, logoSize)
//                    .onlyScaleDown()
//                    .into(source_logo_view)
            } catch (e: Throwable) {
                Logger.e(e)
            }

        }
    }

    override fun setSelected(selected: Boolean) {
        val color = if (selected) primaryLight else Color.WHITE
        setCardBackgroundColor(color)
    }

    override fun onClick(v: View) {
        mEntry?.let {
            cardSelection?.onSelected(it)
            @RequestParam.SortBy val sortBy = ListUtil.getFirst(it.sortBysAvailable)
            requestViewModel.createArticleRequest(it.id!!, sortBy)
        }
    }

    interface CardSelection {
        fun onSelected(selectedEntry: Source.Entry)
    }

    // region Lifecycle
    override fun getLifecycle(): Lifecycle {
        return mLifecycleRegistry
    }
    // endregion
}
