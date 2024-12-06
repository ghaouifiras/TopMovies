package com.firas.myapplication


interface AppDestination {
    val icon: Int
    val route: String
}


object MoviesDestination : AppDestination {
    override val icon = R.drawable.movie_icon
    override val route = "movies"

}

object SeriesDestination : AppDestination {
    override val icon = R.drawable.ic_tv
    override val route = "series"

}

object ProfileDestination : AppDestination {
    override val icon = R.drawable.ic_profile
    override val route = "profile"

}

val movieTabRowScreens = listOf(MoviesDestination, SeriesDestination, ProfileDestination)
