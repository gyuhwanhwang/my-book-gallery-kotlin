package com.mybookgal.mybookgallery.application.port.out

import com.mybookgal.mybookgallery.domain.MyBook

interface LoadMyBookPort {

    fun loadMyBooks(): List<MyBook>
}