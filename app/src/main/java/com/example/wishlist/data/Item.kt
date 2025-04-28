package com.example.wishlist.data

data class Item(
    val id: Int,
    var title: String,
    val desc: String,
    var isPrivate: Boolean
)
