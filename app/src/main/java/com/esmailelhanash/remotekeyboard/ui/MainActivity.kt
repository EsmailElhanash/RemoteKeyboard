package com.esmailelhanash.remotekeyboard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esmailelhanash.remotekeyboard.ui.allLayoutsScreen.AllLayoutsRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.KeyboardLayoutRoot
import com.esmailelhanash.remotekeyboard.ui.theme.RemoteKeyboardTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemoteKeyboardTheme {
                val navController = rememberNavController()
                val allLayoutsViewModel: LayoutsViewModel = viewModel()
                NavHost(navController = navController, startDestination = AllLayoutsScreen) {
                    composable(AllLayoutsScreen) {
                        AllLayoutsRoot(navController = navController,allLayoutsViewModel)
                    }
                    composable(KeyboardLayoutScreen) {
                        KeyboardLayoutRoot(
                            viewModel = allLayoutsViewModel
                        )
                    }
                }

            }
        }
    }



}

