package com.mybookgal.mybookgallery.adapter.out.persistence

import com.mybookgal.mybookgallery.adapter.out.persistence.QMyBookJpaEntity.myBookJpaEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils

class QuerydslMyBookImpl(
    private val queryFactory: JPAQueryFactory
): QuerydslMyBook {

    override fun pagingByOrderByAddedAtDesc(pageable: Pageable): Page<MyBookJpaEntity> {
        val content = queryFactory
            .select(myBookJpaEntity)
            .from(myBookJpaEntity)
            .orderBy(myBookJpaEntity.addedAt.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize)
            .fetch()

        val countQuery = queryFactory
            .select(myBookJpaEntity.count())
            .from(myBookJpaEntity)

        return PageableExecutionUtils.getPage(content, pageable) { countQuery.fetchOne() ?: 0L }
    }
}

