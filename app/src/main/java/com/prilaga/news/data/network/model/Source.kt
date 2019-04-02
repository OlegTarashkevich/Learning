package com.prilaga.news.data.network.model

import com.prilaga.data.serialization.BaseSettings
import org.json.JSONObject

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
        @RequestParam.Category var category_: String? = RequestParam.ALL,
        @RequestParam.Language private var language_: String? = RequestParam.ALL,
        @RequestParam.Country private var country_: String? = RequestParam.ALL
    ) : BaseSettings() {

        var category: String? = RequestParam.parameter(category_)
        var language: String? = RequestParam.parameter(language_)
        var country: String? = RequestParam.parameter(country_)

        override fun loadDefault() {
            this.category = RequestParam.ALL
            this.language = RequestParam.ALL
            this.country = RequestParam.ALL
        }

        override fun serialize(jsonObject: JSONObject) {
            jsonObject.put("category", category)
            jsonObject.put("language", language)
            jsonObject.put("country", country)
        }

        override fun deserialize(jsonObject: JSONObject) {
            category = jsonObject.optString("category", RequestParam.ALL)
            language = jsonObject.optString("language", RequestParam.ALL)
            country = jsonObject.optString("country", RequestParam.ALL)
        }

        override fun classKey(): String {
            return TAG
        }

        companion object {
            val TAG = "Source.Param"

            fun emptyParam(): Param {
                return param(null, null, null)
            }

            fun defaultParam(): Param {
                return param(RequestParam.ALL, RequestParam.ALL, RequestParam.ALL)
            }

            fun param(@RequestParam.Category category: String?, @RequestParam.Language language: String?, @RequestParam.Country country: String?): Param {
                return Param(category, language, country)
            }
        }
    }

}
