package com.example.wishlist

sealed class NavRoutes(val route: String) {
    data object Home: NavRoutes("home")
    data object MyWishLists: NavRoutes("my wish lists")
    data object MyGroups: NavRoutes("my groups")
    data object Reserved: NavRoutes("reserved")
    data object Settings: NavRoutes("settings")
}

