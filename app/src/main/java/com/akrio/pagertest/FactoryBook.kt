package com.akrio.pagertest

class FactoryBook private constructor(
    val title: String?,
    val author: String?
) {

    data class Builder(
        var title: String? = null,
        var author: String? = null
    ){
        fun title (title: String) = apply { this.title = title }
        fun author (author: String) = apply { this.author = author }
        fun build(): FactoryBook = FactoryBook(title, author)
    }
}