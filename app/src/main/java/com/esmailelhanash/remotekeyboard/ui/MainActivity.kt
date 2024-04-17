package com.esmailelhanash.remotekeyboard.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esmailelhanash.remotekeyboard.ui.allLayoutsScreen.composables.AllLayoutsRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.composables.KeyboardLayoutRoot
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // the view model:
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = AllLayoutsScreen) {
                composable(AllLayoutsScreen) {
                    AllLayoutsRoot(navController = navController)
                }
                composable(KeyboardLayoutScreen) {
                    // convert to landscape on navigate to this screen:
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    KeyboardLayoutRoot(
                        navController = navController
                    )
                }
            }

            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    ComposeView(context).apply {
                        // Set the content of the ComposeView to be whatever Compose content you have
                        // If you just need the fragment, you might not need to set any Compose content here
                        setContent {
                            // Compose UI content
                        }
                        // Use this ComposeView as the container for the fragment
                        id = android.R.id.content // Ensure the ComposeView has an ID for the fragment transaction
                    }
                }
            )

        }
    }



}

