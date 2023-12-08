package teka.android.firebase_image_upload.navigation

const val ROOT_GRAPH_ROUTE = "root_graph_route"
const val AUTH_GRAPH_ROUTE = "auth_graph_route"
const val MAIN_GRAPH_ROUTE = "main_graph_route"
const val To_MAIN_GRAPH_ROUTE = "to_main_graph_route"


sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object MultiplePhotoPicker : Screen(route = "multiple_photo_screen")
    object SinglePhotoPicker : Screen(route = "single_photo_screen")
    object SingleVideoPicker : Screen(route = "single_video_screen")
    object VideoPlayerScreen: Screen(route = "video_player_screen")
    object ImagesDisplayScreen: Screen(route = "images_display_screen")


}