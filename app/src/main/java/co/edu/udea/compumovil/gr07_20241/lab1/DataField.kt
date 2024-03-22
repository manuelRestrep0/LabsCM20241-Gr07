package co.edu.udea.compumovil.gr07_20241.lab1

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.Black
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.focusedTextFieldText
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.textFieldContainer
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.unfocusedTextFieldText


@Composable
fun DataTextField(
    text: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType,
    required: Boolean = false,
){

    val uiColor = if(isSystemInDarkTheme()) Color.White else Black

    TextField(
        modifier = modifier,
        value = text,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        onValueChange = onTextChange,
        label = {
            if(required) {
                Text(
                text = "$label*",
                color = uiColor)
            }
            else {
                Text(
                text = label,
                color = uiColor)
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
            focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
            unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
            focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer

        )
    )

}