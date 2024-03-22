package co.edu.udea.compumovil.gr07_20241.lab1

import android.app.DatePickerDialog
import android.content.res.Configuration
import android.widget.DatePicker
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import co.edu.udea.compumovil.gr07_20241.lab1.navigation.Screens
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.Black
import java.util.Calendar
import java.util.Date

@Composable
fun ContactData(controller: NavController) {

    val title = stringResource(R.string.screen_name_contact)

    var phone by rememberSaveable { mutableStateOf("") }
    var mail by rememberSaveable { mutableStateOf("") }

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            ConstraintLayout {
                val (form) = createRefs()
                Column(modifier = Modifier.constrainAs(form) {
                    top.linkTo(parent.top)
                }) {

                    TopSection(title)

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp)
                    ) {
                        DataTextField(
                            text = phone,
                            label = stringResource(R.string.phone),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Phone,
                            required = true,
                            onTextChange = { phone = it })
                        Spacer(modifier = Modifier.height(15.dp))
                        DataTextField(
                            text = mail,
                            label = stringResource(R.string.email),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Email,
                            required = false,
                            onTextChange = { mail = it })
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                }

            }
        }

        Configuration.ORIENTATION_LANDSCAPE -> {
            ConstraintLayout {
                val (form) = createRefs()
                Column(modifier = Modifier.constrainAs(form) {
                    top.linkTo(parent.top)
                }) {

                    TopSection(title)

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row (
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            DataTextField(
                                text = phone,
                                label = stringResource(R.string.phone),
                                keyboardType = KeyboardType.Phone,
                                required = true,
                                onTextChange = { phone = it })
                            DataTextField(
                                text = mail,
                                label = stringResource(R.string.email),
                                keyboardType = KeyboardType.Email,
                                required = false,
                                onTextChange = { mail = it })

                        }

                    }


                }

            }
        }


    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        DataButton(
            modifier = Modifier
                .padding(20.dp)
                .width(200.dp)
                .height(50.dp),
            text = stringResource(R.string.next),
            onClick = { controller.navigate(route = Screens.ContactData.route) })

    }
}
