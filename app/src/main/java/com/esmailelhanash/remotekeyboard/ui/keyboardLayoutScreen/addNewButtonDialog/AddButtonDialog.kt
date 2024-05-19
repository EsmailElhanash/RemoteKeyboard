package com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.addNewButtonDialog


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esmailelhanash.remotekeyboard.data.model.KeyboardButton
import com.esmailelhanash.remotekeyboard.data.model.KeyboardLayout
import com.esmailelhanash.remotekeyboard.data.repository.KeyboardLayoutRepository
import com.esmailelhanash.remotekeyboard.ui.LayoutsViewModel
import com.esmailelhanash.remotekeyboard.ui.common.DialogRoot
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ActionButtons
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ColorSelectDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.ColorSelectorRow
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.DialogTitle
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.FormTextField
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.IconSelectDialog
import com.esmailelhanash.remotekeyboard.ui.keyboardLayoutScreen.IconSelectorRow
import com.esmailelhanash.remotekeyboard.ui.theme.BlackBean
import com.esmailelhanash.remotekeyboard.ui.theme.Champagne
import com.esmailelhanash.remotekeyboard.ui.theme.OldRose
import com.esmailelhanash.remotekeyboard.utils.colorsList
import com.esmailelhanash.remotekeyboard.utils.iconsList
import com.esmailelhanash.remotekeyboard.utils.toName
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun AddNewButtonDialog(layoutsViewModel: LayoutsViewModel, dismiss: () -> Unit) {
    val addButtonViewModel: AddButtonViewModel = viewModel()

    // todo remove this
    fillForm(addButtonViewModel)

    DialogRoot{
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            DialogTitle("Add New Button")
            FormTextField(
                label = "Button Name",
                value = addButtonViewModel.buttonName,
                onValueChange = {
                    addButtonViewModel.buttonName = it
                },
                keyboardType = KeyboardType.Text
            )
            FormTextField(
                label = "Key Stroke",
                value = addButtonViewModel.keyStroke,
                onValueChange = {
                    addButtonViewModel.keyStroke = it
                },
                keyboardType = KeyboardType.Text
            )
            FormTextField(
                label = "Width",
                value = addButtonViewModel.width,
                onValueChange = {
                    addButtonViewModel.width = it
                },
                keyboardType = KeyboardType.Number
            )
            FormTextField(
                label = "Height",
                value = addButtonViewModel.height,
                onValueChange = {
                    addButtonViewModel.height = it
                },
                keyboardType = KeyboardType.Number
            )

            IconSelectorRow(
                addButtonViewModel.selectedIcon ?: Icons.Default.AddCircle
            ){
                addButtonViewModel.showIconsDialog = true
            }
            ColorSelectorRow(
                selectedColor = addButtonViewModel.selectedBGColor,
                prompt = "Button Background Color",
                onBoxClick = {
                    addButtonViewModel.showColorsDialog = true
                    addButtonViewModel.activeColorSelection = ColorSelectionType.BG_COLOR
                },
            )
            ColorSelectorRow(
                selectedColor = addButtonViewModel.selectedTextColor,
                prompt = "Button Text Color",
                onBoxClick = {
                    addButtonViewModel.showColorsDialog = true
                    addButtonViewModel.activeColorSelection = ColorSelectionType.TEXT_COLOR
                },
            )
            ColorSelectorRow(
                selectedColor = addButtonViewModel.selectedBorderColor,
                prompt = "Button Border Color",
                onBoxClick = {
                    addButtonViewModel.showColorsDialog = true
                    addButtonViewModel.activeColorSelection = ColorSelectionType.BORDER_COLOR
                },
            )
            // spacer
            Spacer(modifier = Modifier.height(16.dp))
            // a row for confirm or cancel buttons
            ActionButtons(onCancel = {
                    dismiss()
                },
                onConfirm = confirm(
                    addButtonViewModel,
                    layoutsViewModel,
                    dismiss
                )
            )
        }
    }
    PopupComposable(addButtonViewModel = addButtonViewModel)
}

@Composable
private fun PopupComposable(addButtonViewModel: AddButtonViewModel){
    if (addButtonViewModel.showIconsDialog) {
        IconSelectDialog{
            addButtonViewModel.showIconsDialog = false
            addButtonViewModel.selectedIcon = it

            // show it
        }
    }

    if (addButtonViewModel.showColorsDialog){
        ColorSelectDialog{
            addButtonViewModel.showColorsDialog = false
            addButtonViewModel.setSelectedColor(it)
        }
    }

    if (addButtonViewModel.inCompleteFields) {
        // Show an error message with Snackbar
        Snackbar(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Please fill all the fields")
        }

        // LaunchedEffect to hide the Snackbar after a delay
        LaunchedEffect(key1 = addButtonViewModel.inCompleteFields) {
            delay(3000) // Delay in milliseconds, adjust as needed
            addButtonViewModel.inCompleteFields = false // Hide the Snackbar after the delay
        }
    }

    if (addButtonViewModel.noSelectedLayout) {
        // Show an error message with Snackbar
        Snackbar(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Error, no selected Layout")
        }

        // LaunchedEffect to hide the Snackbar after a delay
        LaunchedEffect(key1 = addButtonViewModel.noSelectedLayout) {
            delay(3000) // Delay in milliseconds, adjust as needed
            addButtonViewModel.noSelectedLayout = false // Hide the Snackbar after the delay
        }
    }
}

private fun confirm(
    addButtonViewModel: AddButtonViewModel,
    layoutsViewModel: LayoutsViewModel,
    dismiss: () -> Unit
) = (onConfirm@{
    val result = addButtonViewModel.checkFormCorrectness()
    if (result) {
        val selectedLayout = layoutsViewModel.selectedLayout.value
        if (selectedLayout == null) {
            addButtonViewModel.noSelectedLayout = true
            return@onConfirm
        }

        // add button to the layout
        layoutsViewModel.addButtonToSelectedLayout(
            KeyboardButton(
                layoutID = selectedLayout.id,
                name = addButtonViewModel.buttonName.text,
                keystroke = addButtonViewModel.keyStroke.text,
                iconName = addButtonViewModel.selectedIcon?.toName(),
                x = 0,
                y = 0,
                width = addButtonViewModel.width.text.toInt(),
                height = addButtonViewModel.height.text.toInt(),
                backgroundColor = addButtonViewModel.selectedBGColor ?: Champagne,
                borderColor = addButtonViewModel.selectedBorderColor ?: OldRose,
                textColor = addButtonViewModel.selectedTextColor ?: BlackBean,
            )
        )
        // dismiss the dialog:
        dismiss()
    } else {
        addButtonViewModel.inCompleteFields = true
    }
})


// preview, landscape:

@Preview(showBackground = true,widthDp = 1920, heightDp = 1080)
@Composable
fun AddNewButtonDialogPreview() {
    AddNewButtonDialog(LayoutsViewModel(mockKeyboardLayoutRepository())){}
}

private fun mockKeyboardLayoutRepository(): KeyboardLayoutRepository {

    // return some mock data
    return object : KeyboardLayoutRepository {
        override suspend fun getKeyboardLayouts(): List<KeyboardLayout> {
            return listOf()
        }

        override suspend fun insertKeyboardLayout(keyboardLayout: KeyboardLayout) {
            // do nothing
        }

        override suspend fun updateKeyboardLayout(keyboardLayout: KeyboardLayout) {

        }

        override suspend fun updateLayoutButtons(
            layout: KeyboardLayout,
            buttons: List<KeyboardButton>
        ) {

        }
    }

}





@Preview
@Composable
fun ColorSelectDialogPreview() {
    ColorSelectDialog {
        // do nothing
    }
}

@Preview(showBackground = true)
@Composable
fun IconSelectDialogPreview() {
    IconSelectDialog {
        // do nothing
    }
}


// a help fun to fill the form when in debug mode, takes the viewmodel as a parameter and fills with random data:
private fun fillForm(addButtonViewModel: AddButtonViewModel) {
    addButtonViewModel.buttonName = TextFieldValue("Button")
    addButtonViewModel.keyStroke = TextFieldValue("A")
    addButtonViewModel.width = TextFieldValue("100")
    addButtonViewModel.height = TextFieldValue("100")
    addButtonViewModel.selectedIcon = iconsList[0]
    addButtonViewModel.selectedBGColor = randomColorFromColorList()
    addButtonViewModel.selectedTextColor = randomColorFromColorList()
    addButtonViewModel.selectedBorderColor = randomColorFromColorList()
}


private fun randomColorFromColorList() = colorsList[Random.nextInt(0, colorsList.size)]



