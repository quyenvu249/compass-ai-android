package com.compassai.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.compassai.app.navigation.CompassAiNavHost
import com.compassai.core_ui.theme.CompassAiTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Single Activity — the app has exactly one Activity.
 *
 * Responsibilities:
 * - Install splash screen
 * - Enable edge-to-edge rendering
 * - Set up the Compose content with our theme
 * - Host the root NavHost
 *
 * Everything else (navigation, screens, state) lives in feature modules
 * and is wired through the NavHost.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CompassAiTheme {
                CompassAiNavHost()
            }
        }
    }
}
