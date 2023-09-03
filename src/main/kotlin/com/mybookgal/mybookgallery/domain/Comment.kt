package com.mybookgal.mybookgallery.domain

class Comment (
    var content: String,
    val user: User,
    val myBook: MyBook,
    var id: Long? = null
)