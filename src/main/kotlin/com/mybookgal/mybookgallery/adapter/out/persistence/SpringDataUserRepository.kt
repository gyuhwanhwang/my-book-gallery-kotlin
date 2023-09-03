package com.mybookgal.mybookgallery.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataUserRepository: JpaRepository<UserJpaEntity, Long> {
}