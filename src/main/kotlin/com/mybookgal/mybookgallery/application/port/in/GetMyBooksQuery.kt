package com.mybookgal.mybookgallery.application.port.`in`

import com.mybookgal.mybookgallery.domain.MyBook
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetMyBooksQuery {

    fun getMyBooks(): List<MyBook>
    fun getMyBooksByPage(pageable: Pageable): Page<MyBook>
}