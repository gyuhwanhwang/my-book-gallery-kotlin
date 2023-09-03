package com.mybookgal.mybookgallery.domain

import java.time.LocalDateTime
import kotlin.String as String

class MyBook(
    var title: String,
    val author: User,
    var content: String,
    var addedAt: LocalDateTime = LocalDateTime.now(),
    var id: Long? = null
) {
    private val comments: MutableList<Comment> = mutableListOf()

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun getComments(): List<Comment> = comments.toList()
}