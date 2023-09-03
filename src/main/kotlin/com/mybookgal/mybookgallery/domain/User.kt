package com.mybookgal.mybookgallery.domain

class User(
    var name: String,
    var email: String,
    var profileImage: String?,
    var introduction: String?,
    val role: Role,
    var id: Long? = null
) {
    private val myBooks: MutableList<MyBook> = mutableListOf()

    fun addMyBook(myBook: MyBook) {
        myBooks.add(myBook)
    }

    fun getMyBooks(): List<MyBook> = myBooks.toList()
}