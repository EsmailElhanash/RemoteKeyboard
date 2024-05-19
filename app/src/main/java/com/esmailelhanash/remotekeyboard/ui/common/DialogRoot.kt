package com.esmailelhanash.remotekeyboard.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.esmailelhanash.remotekeyboard.ui.theme.Champagne

@Composable
fun DialogRoot(content: @Composable () -> Unit) {
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = false,),
        content = {
            Surface(
                color = Champagne,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(3.dp, MaterialTheme.colorScheme.outline),
                modifier = Modifier.fillMaxWidth(0.6F).fillMaxHeight()
            ) {
                Box(contentAlignment = Alignment.Center) {
                    content()
                }
            }
        }
    )
}