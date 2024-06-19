package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.editDialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditAction
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.EditViewModel
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeColorsDialog.ChangeColorsDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeFontSizeDialog.ChangeFontSizeDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeIconDialog.ChangeIconDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.changeKeyStrokeDialog.ChangeKeystrokeDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.renameDialog.RenameButtonDialog
import com.esmailelhanash.remotekeyboard.utils.toName

@Composable
fun EditDialogs(onEditConfirm: (KeyboardButton) -> Unit, editViewModel: EditViewModel) {
    editViewModel.theButtonToEdit.observeAsState().value?.let { button ->
        when (editViewModel.editAction.value){
            EditAction.RENAME -> {
                RenameButtonDialog(
                    button = button,
                    onConfirm = { newName ->
                        editViewModel.setEditButton(null)
                        onEditConfirm(button.apply {
                            this.name = newName
                        })
                    },
                    onCancel = {
                        editViewModel.setEditButton(null)
                    }
                )
            }
            EditAction.CHANGE_ICON -> {
                ChangeIconDialog(
                    button = button,
                    onConfirm = { icon ->
                        editViewModel.setEditButton(null)
                        onEditConfirm(button.apply {
                            this.iconName = icon.toName()
                        })
                    },
                    onCancel = {
                        editViewModel.setEditButton(null)
                    }
                )
            }
            EditAction.CHANGE_KEYSTROKE -> {
                ChangeKeystrokeDialog(
                    button = button,
                    onConfirm = { keyStroke ->
                        editViewModel.setEditButton(null)
                        onEditConfirm(button.apply {
                            this.keystroke = keyStroke
                        })
                    },
                    onCancel = {
                        editViewModel.setEditButton(null)
                    }
                )
            }
            EditAction.CHANGE_COLORS -> {
                ChangeColorsDialog(
                    button = button,
                    onConfirm = {
                        editViewModel.setEditButton(null)
                        onEditConfirm(
                            button.apply {
                                this.backgroundColor = it.backgroundColor
                                this.textColor = it.textColor
                                this.borderColor = it.borderColor
                            }
                        )
                    },
                    onCancel = {
                        editViewModel.setEditButton(null)
                    }
                )
            }
            EditAction.CHANGE_FONT_SIZE -> {
                ChangeFontSizeDialog(
                    button = button,
                    onConfirm = {
                        editViewModel.setEditButton(null)
                        onEditConfirm(
                            button.apply {
                                this.fontSize = it
                            }
                        )
                    },
                    onCancel = {
                        editViewModel.setEditButton(null)
                    }
                )
            }
            else -> {}
        }


    }

}