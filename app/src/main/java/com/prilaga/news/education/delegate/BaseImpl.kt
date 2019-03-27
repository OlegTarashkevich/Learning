package com.prilaga.news.education.delegate

/**
 * Created by Oleg Tarashkevich on 26/03/2019.
 */
class BaseImpl(val x: Int) : Base {

    override fun printIt() {
        print(x)
    }
}