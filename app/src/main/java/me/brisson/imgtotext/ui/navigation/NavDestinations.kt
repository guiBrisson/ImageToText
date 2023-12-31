package me.brisson.imgtotext.ui.navigation

object AppNavigationScreens {
    const val main_screen = "main"
    const val result_screen = "result"
}

object AppNavigationArgs {
    const val uri_args = "uri"
}

object AppNavigationRoutes {
    const val main_route = AppNavigationScreens.main_screen
    const val result_route = "${AppNavigationScreens.result_screen}?{${AppNavigationArgs.uri_args}}"
}
