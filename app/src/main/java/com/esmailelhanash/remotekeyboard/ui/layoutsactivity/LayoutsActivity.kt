package com.esmailelhanash.remotekeyboard.ui.layoutsactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables.Root
import com.esmailelhanash.remotekeyboard.ui.theme.RemoteKeyboardTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllKeyboardLayoutsActivity : ComponentActivity() {

    // the view model:
    private val viewModel: KeyboardLayoutsViewModel by viewModels()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemoteKeyboardTheme(dynamicColor = false) {
                Root(
                    viewModel = viewModel
                )
            }
        }
    }



}

