package org.d3if3102.produkapi.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
}