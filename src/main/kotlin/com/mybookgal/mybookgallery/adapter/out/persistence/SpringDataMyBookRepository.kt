package com.mybookgal.mybookgallery.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataMyBookRepository : JpaRepository<MyBookJpaEntity, Long>, QuerydslMyBook {

    fun findAllByOrderByAddedAtDesc(): List<MyBookJpaEntity>
}