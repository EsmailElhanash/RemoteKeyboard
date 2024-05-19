package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeIconDialog

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.IconSelectDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.IconSelectorRow
import com.esmailelhanash.remotekeyboard.utils.toIcon
import com.esmailelhanash.remotekeyboard.utils.toName

@Composable
fun ChangeIconDialog(
    button: KeyboardButton,
    onConfirm: (newIcon: ImageVector) -> Unit,
    onCancel: () -> Unit,
) {
    var newIcon by remember { mutableStateOf(button.iconName) }
    var showIconsDialog by remember { mutableStateOf(false) }
    DialogRoot {
        Column {
            IconSelectorRow(
                selectedIcon = newIcon?.toIcon(),
                onIconClick = {
                    showIconsDialog = true
                }
            )

            ActionButtons(
                onCancel = {
                    onCancel()
                },
                onConfirm = {
                    newIcon?.toIcon()?.let {
                        onConfirm(
                            it
                        )
                    }
                }
            )
        }
    }
    if (showIconsDialog){
        IconSelectDialog(
            onIconSelected = {
                newIcon = it.toName()
                showIconsDialog = false
            }
        )
    }

}

