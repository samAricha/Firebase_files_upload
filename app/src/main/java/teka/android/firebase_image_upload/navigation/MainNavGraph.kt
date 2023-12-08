package teka.android.firebase_image_upload.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import teka.android.firebase_image_upload.components.ImagesDisplayScreen
import teka.android.firebase_image_upload.components.MultiplePhotoPicker
import teka.android.firebase_image_upload.components.SinglePhotoPicker


@Composable
fun MainNavGraph(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Screen.MultiplePhotoPicker.route,
        route = MAIN_GRAPH_ROUTE
    ) {

        composable(
            route = Screen.SinglePhotoPicker.route,
        ){
            SinglePhotoPicker()
        }

        composable(
            route = Screen.MultiplePhotoPicker.route,
        ){
            MultiplePhotoPicker()
        }

        composable(
            route = Screen.ImagesDisplayScreen.route
        ){
            ImagesDisplayScreen()
        }

    }
}