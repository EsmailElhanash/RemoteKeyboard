package com.esmailelhanash.remotekeyboard.ui.layoutsactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository
import com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables.Fab
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
        viewModel.layoutsLiveData.observe(this) { layouts ->

            // print them
            layouts.forEach { layout ->
                println(layout)
            }

        }
        setContent {
            RemoteKeyboardTheme {
                Scaffold(
                    floatingActionButton = { Fab() },
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        Root(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                )
            }
        }
    }



}

