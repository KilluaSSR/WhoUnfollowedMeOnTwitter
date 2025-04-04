package killua.dev.whounfollowedmeontwitter

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import killua.dev.whounfollowedmeontwitter.Model.AvailablePlatforms
import killua.dev.whounfollowedmeontwitter.datastore.writeProtectMe
import killua.dev.whounfollowedmeontwitter.ui.Animations.AnimatedNavHost
import killua.dev.whounfollowedmeontwitter.ui.CookiesRoutes
import killua.dev.whounfollowedmeontwitter.ui.MainRoutes
import killua.dev.whounfollowedmeontwitter.ui.Pages.BrowserPage
import killua.dev.whounfollowedmeontwitter.ui.Pages.MainPage
import killua.dev.whounfollowedmeontwitter.ui.Pages.SettingsPage
import killua.dev.whounfollowedmeontwitter.ui.PrepareRoutes
import killua.dev.whounfollowedmeontwitter.ui.theme.ThemeMode
import killua.dev.whounfollowedmeontwitter.ui.theme.WhoUnfollowedMeOnTwitterTheme
import killua.dev.whounfollowedmeontwitter.ui.theme.observeThemeMode
import killua.dev.whounfollowedmeontwitter.utils.BiometricManagerSingleton
import killua.dev.whounfollowedmeontwitter.utils.LocalNavController
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BiometricManagerSingleton.init(this)

        lifecycleScope.launch {
            if (BiometricManagerSingleton.getBiometricHelper()?.canAuthenticate() == false) {
                writeProtectMe(false)
            }
        }
        if (checkSelfPermission(android.Manifest.permission.USE_BIOMETRIC)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.USE_BIOMETRIC), 123)
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val themeMode by this.observeThemeMode()
                .collectAsStateWithLifecycle(initialValue = ThemeMode.SYSTEM)
            WhoUnfollowedMeOnTwitterTheme(
                themeMode = themeMode
            ) {
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
                            MainPage()
                        }
                        composable(MainRoutes.SubscribePage.route) {
                            //SubscribePage()
                        }
                        composable(MainRoutes.UserinfoPage.route){
                            //UserInfoPage()
                        }
                        composable(MainRoutes.SettingPage.route){
                            SettingsPage()
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
