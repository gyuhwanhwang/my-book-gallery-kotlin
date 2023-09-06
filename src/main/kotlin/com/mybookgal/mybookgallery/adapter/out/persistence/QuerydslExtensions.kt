package com.mybookgal.mybookgallery.adapter.out.persistence

import com.querydsl.jpa.impl.JPAQuery

fun <T> JPAQuery<T>.limit(pageSize: Int): JPAQuery<T> {
    return this.limit(pageSize.toLong())
}
