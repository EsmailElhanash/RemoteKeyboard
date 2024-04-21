package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel

@Composable
fun AddNewButtonDialog(layoutsViewModel: LayoutsViewModel) {

    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(usePlatformDefaultWidth = true,),

        content = {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.width(300.dp).height(200.dp)
            ){
                TextField("",{})
            }
        }
    )
}