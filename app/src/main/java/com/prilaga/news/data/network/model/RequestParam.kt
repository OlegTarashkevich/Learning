package com.prilaga.news.data.network.model

import android.text.TextUtils
import androidx.annotation.StringDef

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Created by Oleg Tarashkevich on 31.03.17.
 */

object RequestParam {

    const val ALL = "all"

    val categories: Array<String>
        @Category get() = arrayOf(
            ALL,
            Category.BUSINESS,
            Category.ENTERTAINMENT,
            Category.GAMING,
            Category.GENERAL,
            Category.MUSIC,
            Category.POLITICS,
            Category.SCIENCE_AND_NATURE,
            Category.SPORT,
            Category.TECHNOLOGY
        )

    val languages: Array<String>
        @Language get() = arrayOf(ALL, Language.EN, Language.DE, Language.FR)

    val countries: Array<String>
        @Country get() = arrayOf(ALL, Country.AU, Country.DE, Country.GB, Country.IN, Country.IT, Country.US)

    @StringDef(ALL, SortBy.TOP, SortBy.LATEST, SortBy.POPULAR)
    @Retention(RetentionPolicy.SOURCE)
    annotation class SortBy {
        companion object {
            const val TOP = "top"
            const val LATEST = "latest"
            const val POPULAR = "popular"
        }
    }

    @StringDef(ALL, Language.EN, Language.DE, Language.FR)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Language {
        companion object {
            const val EN = "en"
            const val DE = "de"
            const val FR = "fr"
        }
    }

    @StringDef(ALL, Country.AU, Country.DE, Country.GB, Country.IN, Country.IT, Country.US)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Country {
        companion object {
            const val AU = "au"
            const val DE = "de"
            const val GB = "gb"
            const val IN = "in"
            const val IT = "it"
            const val US = "us"
        }
    }

    @StringDef(
        ALL,
        Category.BUSINESS,
        Category.ENTERTAINMENT,
        Category.GAMING,
        Category.GENERAL,
        Category.MUSIC,
        Category.POLITICS,
        Category.SCIENCE_AND_NATURE,
        Category.SPORT,
        Category.TECHNOLOGY
    )
    @Retention(RetentionPolicy.SOURCE)
    annotation class Category {
        companion object {
            const val BUSINESS = "business"
            const val ENTERTAINMENT = "entertainment"
            const val GAMING = "gaming"
            const val GENERAL = "general"
            const val MUSIC = "music"
            const val POLITICS = "politics"
            const val SCIENCE_AND_NATURE = "science-and-nature"
            const val SPORT = "sport"
            const val TECHNOLOGY = "technology"
        }
    }

    fun parameter(parameter: String?): String? {
        return if (!TextUtils.isEmpty(parameter) && parameter.equals(
                RequestParam.ALL,
                ignoreCase = true
            )
        ) null else parameter
    }

    fun defaultParam(parameter: String): String {
        return if (TextUtils.isEmpty(parameter)) RequestParam.ALL else parameter
    }
}
