package com.prilaga.news.education.delegate

/**
 * Created by Oleg Tarashkevich on 26/03/2019.
 */
class Derived(b: Base, o: Openable) : Base by b, Openable by o {
}
