package co.edu.udea.compumovil.gr07_20241.lab1

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.Black
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.focusedTextFieldText
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.textFieldContainer
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.unfocusedTextFieldText

@Composable
fun PersonalData(controller: NavController) {
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {

            TopSection()
            Spacer(modifier = Modifier.height(20.dp))

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ){
                DataTextField(
                    text = name,
                    label = stringResource(R.string.names),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Text,
                    required = true,
                    onTextChange = {name = it})
                Spacer(modifier = Modifier.height(15.dp))
                DataTextField(
                    text = lastName,
                    label = stringResource(R.string.last_names),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Text,
                    required = true,
                    onTextChange = {lastName = it})

            }

        }

    }
}

@Composable
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.app_name),
                tint = uiColor
            )
            Spacer(modifier = Modifier.size(42.dp))
            Column {
                Text(
                    text = stringResource(R.string.app_name),
                    color = uiColor
                )
            }
        }
        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}


