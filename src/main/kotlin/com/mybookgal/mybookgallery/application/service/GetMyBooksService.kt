package com.mybookgal.mybookgallery.application.service

import com.mybookgal.mybookgallery.application.port.`in`.GetMyBooksQuery
import com.mybookgal.mybookgallery.application.port.out.LoadMyBookPort
import com.mybookgal.mybookgallery.domain.MyBook
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetMyBooksService(private val loadMyBookPort: LoadMyBookPort) : GetMyBooksQuery {

    override fun getMyBooks(): List<MyBook>
        = loadMyBookPort.loadMyBooks()


    override fun getMyBooksByPage(pageable: Pageable): Page<MyBook>
        = loadMyBookPort.loadMyBooksByPage(pageable)

}