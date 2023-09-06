package com.mybookgal.mybookgallery.adapter.out.persistence

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface QuerydslMyBook {

    fun pagingByOrderByAddedAtDesc(pageable: Pageable): Page<MyBookJpaEntity>
}