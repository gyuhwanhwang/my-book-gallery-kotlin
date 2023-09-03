package com.mybookgal.mybookgallery.application.port.`in`

import com.mybookgal.mybookgallery.domain.MyBook

interface GetMyBooksQuery {

    fun getMyBooks(): List<MyBook>
}