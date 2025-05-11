package com.example.wishlist.data

data class User(
    val id: Int,
    val userName: String,
    val email: String,
    val password: String,
    val lists: List<Wishlist>
)
