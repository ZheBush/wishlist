package com.example.wishlist.data

data class Wishlist(
    val id: Int,
    val title: String,
    val isPrivate: Boolean,
    val list: List<Item>
)
