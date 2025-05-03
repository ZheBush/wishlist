package com.example.wishlist.data

data class Group(
    val id: Int,
    val title: String,
    val list: List<User>
)
