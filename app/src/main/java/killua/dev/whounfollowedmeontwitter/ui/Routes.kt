package killua.dev.whounfollowedmeontwitter.ui

sealed class MainRoutes(val route: String) {
    data object MainPage : MainRoutes(route = "main_page")
    data object UserinfoPage : MainRoutes(route = "userinfo_page")
    data object SubscribePage : MainRoutes(route = "subscribe_page")
    data object ReportPage : MainRoutes(route = "report_page")
    data object SettingPage : MainRoutes(route = "setting_page")
    data object AboutPage : MainRoutes(route = "about_page")
    data object HelpPage : MainRoutes(route = "help_page")
}

sealed class PrepareRoutes(val route: String){
    data object TwitterPreparePage: PrepareRoutes(route = "twitter_prepare_page")
}
sealed class CookiesRoutes(val route: String){
    data object TwitterCookiesBrowser: CookiesRoutes(route = "Twitter_cookies_browser")
}