package com.prilaga.news.data.network.model

/**
 * Created by Oleg Tarashkevich on 31/03/2017.
 */

class Source {

    val status: String? = null
    val sources: List<Entry>? = null

    class Entry {
        val id: String? = null
        val name: String? = null
        val description: String? = null
        val url: String? = null
        val category: String? = null
        val language: String? = null
        val country: String? = null
        val urlsToLogos: Logos? = null
        val sortBysAvailable: List<String>? = null

        val info: String
            get() = "$name | $category"
    }

    class Logos {
        val small: String? = null
        val medium: String? = null
        val large: String? = null
    }

    data class Param constructor(
        @RequestParam.Category private var category_: String? = null,
        @RequestParam.Language private var language_: String? = null,
        @RequestParam.Country private var country_: String? = null
    ) {

        val category: String?
        val language: String?
        val country: String?

        init {
            this.category = RequestParam.parameter(category_)
            this.language = RequestParam.parameter(language_)
            this.country = RequestParam.parameter(country_)
        }

        companion object {
            val TAG = "Source.Param"
        }
    }

    companion object {

        fun emptyParam(): Param {
            return param(null, null, null)
        }

        fun defaultParam(): Param {
            return param(RequestParam.ALL, RequestParam.ALL, RequestParam.ALL)
        }

        fun param(@RequestParam.Category category: String?, @RequestParam.Language language: String?, @RequestParam.Country country: String?): Param {
            return Param(category, language, country)
        }

        fun saveParam(param: Param) {

        }
    }
}
