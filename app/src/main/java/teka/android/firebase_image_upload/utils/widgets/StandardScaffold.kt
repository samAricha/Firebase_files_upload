package teka.android.firebase_image_upload.utils.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import teka.android.firebase_image_upload.models.BottomNavItem
import teka.android.firebase_image_upload.ui.theme.DarkBlue
import teka.android.firebase_image_upload.ui.theme.DarkerYellow
import teka.android.firebase_image_upload.ui.theme.MainWhiteColor
import teka.android.firebase_image_upload.ui.theme.YellowMain


@Composable
fun StandardScaffold(
    navController: NavHostController,
    showBottomBar: Boolean = true,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.Home,
        BottomNavItem.Store
    ),
    content: @Composable (paddingValues: PaddingValues) -> Unit,
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(
                    backgroundColor = MainWhiteColor,
                    contentColor = Color.White,
                    elevation = 5.dp
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { item ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painterResource(id = item.icon),
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontSize = 9.sp
                                )
                            },
                            selectedContentColor = DarkerYellow,
                            unselectedContentColor = DarkBlue,
                            alwaysShowLabel = true,
                            selected = currentDestination?.route?.startsWith(item.destination) == true,
                            onClick = {
                                navController.navigate(item.destination) {
                                    navController.graph.startDestinationRoute?.let { screen_route ->
                                        popUpTo(screen_route) {
                                            saveState = true
                                        }
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}