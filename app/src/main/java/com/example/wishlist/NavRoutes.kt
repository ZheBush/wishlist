package com.example.wishlist

sealed class NavRoutes(val route: String) {
    data object Home: NavRoutes("home")
    data object MyWishList: NavRoutes("my wish list")
    data object MyGroups: NavRoutes("my groups")
    data object Reserved: NavRoutes("reserved")
    data object Settings: NavRoutes("settings")
}

