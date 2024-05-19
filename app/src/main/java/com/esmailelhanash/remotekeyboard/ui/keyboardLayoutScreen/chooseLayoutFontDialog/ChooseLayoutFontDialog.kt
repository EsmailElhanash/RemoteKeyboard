package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.chooseLayoutFontDialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.allLayoutsScreen.mockLayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ActionButtons
import com.esmailelhanash.remotekeyboard.utils.fontsMap


@Composable
fun ChooseLayoutFontDialog(
    layoutsViewModel: LayoutsViewModel,
    dismiss: () -> Unit,
){
    var chosenFont by remember { mutableStateOf(layoutsViewModel.selectedLayout.value?.font)}
    DialogRoot {
        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(
                    rememberScrollState()
                ),
        ){
            Text("Choose Font:")
            fontsMap.forEach {
                FontSelectorRow(
                    font = it,
                    onFontClick = {
                        chosenFont = it.key
                    }
                )
            }
            ActionButtons(
                onCancel = {
                    dismiss()
                },
                onConfirm = {
                    layoutsViewModel.changeLayoutFont(chosenFont)
                    dismiss()
                }
            )
        }
    }
}
@Composable
fun FontSelectorRow(font: Map.Entry<String, FontFamily>, onFontClick: () -> Unit) {
    Text(
        text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789",
        fontFamily = font.value,
        modifier = Modifier.padding(
            16.dp
        ).clickable { onFontClick() }
    )
}


@Preview
@Composable
private fun ChooseLayoutFontDialogPreview() {
    ChooseLayoutFontDialog(
        layoutsViewModel = mockLayoutsViewModel(
        ),
        dismiss = {}
    )
}