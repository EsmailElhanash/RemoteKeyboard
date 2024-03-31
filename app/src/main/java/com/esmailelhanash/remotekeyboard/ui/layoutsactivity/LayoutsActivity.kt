package com.esmailelhanash.remotekeyboard.ui.layoutsactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables.Fab
import com.esmailelhanash.remotekeyboard.ui.layoutsactivity.composables.Root
import com.esmailelhanash.remotekeyboard.ui.theme.RemoteKeyboardTheme

class AllKeyboardLayoutsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

