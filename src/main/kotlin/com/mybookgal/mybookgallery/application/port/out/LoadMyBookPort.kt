package com.mybookgal.mybookgallery.application.port.out

import com.mybookgal.mybookgallery.domain.MyBook
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface LoadMyBookPort {

    fun loadMyBooks(): List<MyBook>
    fun loadMyBooksByPage(pageable: Pageable): Page<MyBook>
}