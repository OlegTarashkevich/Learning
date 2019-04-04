package com.prilaga.news.data.network.model

import android.text.TextUtils
import com.prilaga.data.utils.DateUtil
import com.prilaga.data.utils.SimpleDateFormatThreadSafe
import java.util.*

/**
 * Created by Oleg Tarashkevich on 29/03/2017.
 */

class Article {

    private val status: String? = null
    private val sortBy: String? = null
    private val articles: List<Entry>? = null

    class Entry {
        private val author: String? = null
        private val title: String? = null
        private val description: String? = null
        private val url: String? = null
        private val urlToImage: String? = null
        private val publishedAt: Date? = null

        private var publishedDate = ""

        val info: String
            get() = getAuthor() + " | " + getPublishedDate()

        fun getAuthor(): String {
            return author ?: ""
        }

        fun getPublishedDate(): String {
            if (publishedAt != null && TextUtils.isEmpty(publishedDate))
                publishedDate = DateUtil.dateToString(publishedAt, dateAndTimeFormat)
            return publishedDate
        }
    }

    data class Param constructor(private val source_: String, @RequestParam.SortBy private val sortBy_: String) {
        var source: String?
        var sortBy: String?

        init {
            this.source = RequestParam.parameter(source_)
            this.sortBy = RequestParam.parameter(sortBy_)
        }
    }

    companion object {

        val dateAndTimeFormat = SimpleDateFormatThreadSafe("MMMM dd yyy, hh:mm:ss", Locale.getDefault())

        fun createParam(source: String, @RequestParam.SortBy sortBy: String): Param {
            return Param(source, sortBy)
        }
    }
}
