package killua.dev.whounfollowedmeontwitter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import killua.dev.whounfollowedmeontwitter.Model.AvailablePlatforms
import killua.dev.whounfollowedmeontwitter.ui.Animations.AnimatedNavHost
import killua.dev.whounfollowedmeontwitter.ui.CookiesRoutes
import killua.dev.whounfollowedmeontwitter.ui.MainRoutes
import killua.dev.whounfollowedmeontwitter.ui.Pages.BrowserPage
import killua.dev.whounfollowedmeontwitter.ui.PrepareRoutes
import killua.dev.whounfollowedmeontwitter.ui.theme.WhoUnfollowedMeOnTwitterTheme
import killua.dev.whounfollowedmeontwitter.utils.LocalNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhoUnfollowedMeOnTwitterTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(
                    LocalNavController provides navController,
                    LocalLifecycleOwner provides LocalLifecycleOwner.current
                ) {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = MainRoutes.MainPage.route
                    ) {
                        composable(MainRoutes.MainPage.route) {
                            //MainPage()
                        }
                        composable(MainRoutes.SubscribePage.route) {
                            //SubscribePage()
                        }
                        composable(MainRoutes.UserinfoPage.route){
                            //UserInfoPage()
                        }
                        composable(MainRoutes.SettingPage.route){
                           // SettingsPage()
                        }
                        composable(MainRoutes.AboutPage.route){
                           // AboutPage()
                        }

                        composable(PrepareRoutes.TwitterPreparePage.route){
                            //TwitterPreparePage()
                        }

                        composable(CookiesRoutes.TwitterCookiesBrowser.route){
                            BrowserPage(AvailablePlatforms.Twitter)
                        }
                    }
                }
            }
        }
    }
}
