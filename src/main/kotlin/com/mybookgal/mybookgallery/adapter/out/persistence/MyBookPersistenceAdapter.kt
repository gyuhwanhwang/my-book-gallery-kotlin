package com.mybookgal.mybookgallery.adapter.out.persistence

import com.mybookgal.mybookgallery.application.port.out.LoadMyBookPort
import com.mybookgal.mybookgallery.domain.MyBook
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class MyBookPersistenceAdapter(private val myBookRepository: SpringDataMyBookRepository) : LoadMyBookPort {

    private val log = LoggerFactory.getLogger(MyBookPersistenceAdapter::class.java)

    override fun loadMyBooks(): List<MyBook> {
        log.info("loadMyBooks")
        val myBookEntities = myBookRepository.findAllByOrderByAddedAtDesc()
        return myBookEntities.map { it.toDomainModel() }
    }

    override fun loadMyBooksByPage(pageable: Pageable): Page<MyBook> {
        return myBookRepository.pagingByOrderByAddedAtDesc(pageable).map { it.toDomainModel() }
    }
}