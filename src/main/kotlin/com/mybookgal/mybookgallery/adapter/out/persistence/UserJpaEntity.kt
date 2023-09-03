package com.mybookgal.mybookgallery.adapter.out.persistence

import com.mybookgal.mybookgallery.domain.Role
import com.mybookgal.mybookgallery.domain.User
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserJpaEntity(
    @Id @GeneratedValue
    var id: Long? = null,
    var name: String,
    var email: String,
    var profileImage: String? = null,
    var introduction: String? = null,
    @Enumerated(EnumType.STRING)
    var role: Role
) {

    fun toDomainModel(): User {
        return User(
            name = this.name,
            email = this.email,
            profileImage = this.profileImage,
            introduction = this.introduction,
            role = this.role,
            id = this.id
        )
    }
}
