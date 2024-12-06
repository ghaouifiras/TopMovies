package com.firas.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.firas.myapplication.presentation.components.movieDetails.MovieDetails
import com.firas.myapplication.presentation.components.movieList.MovieScreen
import com.firas.myapplication.presentation.components.profile_screen.ProfileScreen
import com.firas.myapplication.presentation.components.series_list.Series_Screen
import com.firas.myapplication.presentation.viewmodels.MovieDetailViewModel
import com.firas.myapplication.presentation.viewmodels.MoviesViewModel
import com.firas.myapplication.ui.theme.TopMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopMoviesTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination
                val currentScreen =
                    movieTabRowScreens.find { it.route == currentDestination?.route }
                        ?: MoviesDestination


                Scaffold(
                    bottomBar = {
                        MovieTabRow(
                            allScreens = movieTabRowScreens,
                            onTabSelected = { screen -> navController.navigateSingleTopTo(screen.route) },
                            currentScreen = currentScreen
                        )
                    }
                ) { innerPadding ->
                    var viewModel: MoviesViewModel = hiltViewModel()
                    NavHost(
                        navController = navController,
                        startDestination = "movies",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("movies") { MovieScreen(viewModel.moviesFlow, navController) }
                        composable("series") { Series_Screen() }
                        composable("profile") { ProfileScreen() }
                        composable(
                            route = "details/{movieId}",
                            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val viewModel: MovieDetailViewModel = hiltViewModel()
                            MovieDetails(viewModel)
                        }
                    }


                }
            }
        }
    }

}


fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TopMoviesTheme {
    }
}