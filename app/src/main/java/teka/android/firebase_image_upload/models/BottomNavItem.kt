package teka.android.firebase_image_upload.models

import teka.android.firebase_image_upload.R
import teka.android.firebase_image_upload.navigation.Screen

sealed class BottomNavItem(var title: String, var icon: Int, var destination: String) {
    object Home : BottomNavItem(
        title = "Upload",
        icon = R.drawable.ic_home,
        destination = Screen.MultiplePhotoPicker.route
    )
//    object Favorites: BottomNavItem(
//        title = "Favorites",
//        icon = R.drawable.ic_star,
//        destination = Screen.FavouritesScreen.route
//    )
    object Store: BottomNavItem(
        title = "Store",
        icon = R.drawable.ic_basket,
        destination = Screen.ImagesDisplayScreen.route
    )
//    object Account: BottomNavItem(
//        title = "Account",
//        icon = R.drawable.ic_basket,
//        destination = Screen.AccountScreen.route
//    )
}