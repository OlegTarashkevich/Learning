package com.prilaga.news.view.widget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.prilaga.data.utils.ListUtil
import com.prilaga.data.utils.Logger
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Article
import com.prilaga.news.data.network.model.RequestParam
import com.prilaga.news.data.network.model.Source
import com.prilaga.news.util.TextUtil
import kotlinx.android.synthetic.main.cardview_source.view.*

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

class SourceCardView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    CardView(context, attrs, defStyleAttr), View.OnClickListener {

//    @BindView(R.id.source_logo_view)
//    internal var logoImageView: ImageView? = null
//    @BindView(R.id.source_name_text_view)
//    internal var nameTextView: TextView? = null
//    @BindView(R.id.source_description_text_view)
//    internal var descriptionTextView: TextView? = null
//    @BindView(R.id.source_url_text_view)
//    internal var urlTextView: TextView? = null

    internal val logoSize: Int
        get() = context.resources.getDimension(R.dimen.z_size).toInt()
    internal val primaryLight: Int
        get() = ContextCompat.getColor(context, R.color.primary_light)
    val options: RequestOptions by lazy {
        RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.ic_newspaper)
            .override(logoSize, logoSize)
            .priority(Priority.HIGH)
    }

    private var mEntry: Source.Entry? = null
    var cardSelection: CardSelection? = null

    init {
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

            @RequestParam.SortBy val sortBy = ListUtil.getFirst(mEntry!!.sortBysAvailable)
//            EventBus.getDefault().post(Article.createParam(mEntry!!.id, sortBy))
        }
    }

    interface CardSelection {
        fun onSelected(selectedEntry: Source.Entry)
    }
}
