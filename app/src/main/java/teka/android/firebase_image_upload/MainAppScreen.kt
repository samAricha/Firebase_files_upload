package teka.android.firebase_image_upload

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import teka.android.firebase_image_upload.navigation.MainNavGraph
import teka.android.firebase_image_upload.navigation.Screen
import teka.android.firebase_image_upload.utils.widgets.StandardScaffold


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainAppScreen() {
    val navController = rememberNavController()
    val newBackStackEntry by navController.currentBackStackEntryAsState()
    val route = newBackStackEntry?.destination?.route

    StandardScaffold(
        navController = navController,
        showBottomBar = route in listOf(
            Screen.MultiplePhotoPicker.route,
//            Screen.FavouritesScreen.route,
            Screen.ImagesDisplayScreen.route
//                            Screen.AccountScreen.route
        )
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavGraph(navController = navController)
        }
    }


}