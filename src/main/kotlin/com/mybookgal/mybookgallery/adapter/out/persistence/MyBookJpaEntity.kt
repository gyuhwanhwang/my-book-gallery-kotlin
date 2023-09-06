package com.mybookgal.mybookgallery.adapter.out.persistence

import com.mybookgal.mybookgallery.domain.MyBook
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "my_books")
class MyBookJpaEntity(
    @Id @GeneratedValue
    var id: Long? = null,
    var title: String,
    var content: String,
    @Column(name = "added_at")
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var author: UserJpaEntity
) {

    fun toDomainModel(): MyBook {
        return MyBook(
            title = this.title,
            content = this.content,
            author = this.author.toDomainModel(),
            addedAt = this.addedAt,
            id = this.id
        )
    }
}
