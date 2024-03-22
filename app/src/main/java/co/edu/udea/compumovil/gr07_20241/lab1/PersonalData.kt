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
fun PersonalData(controller: NavController) {
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }

    var genderOptions = listOf(stringResource(R.string.female), stringResource(R.string.male))
    val selectedOption = rememberSaveable { mutableStateOf("") }

    val birthName = stringResource(R.string.birth_day)
    val birthDate = rememberSaveable { mutableStateOf("$birthName*") }

    val scholarGrade = stringResource(R.string.grade)
    val scholarSelected = rememberSaveable{mutableStateOf(scholarGrade)}

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            PersonalPortraitOrientation(name, lastName, genderOptions, selectedOption, birthDate, scholarSelected)
        } Configuration.ORIENTATION_LANDSCAPE -> {}
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ){
        DataButton(
            modifier = Modifier
                .padding(20.dp)
                .width(200.dp)
                .height(50.dp),
            text = stringResource(R.string.next),
            onClick = {controller.navigate(route = Screens.ContactData.route)})

    }
}

//Formulario orientacion vertical
@Composable
private fun PersonalPortraitOrientation(name: String, lastName: String, genderOptions: List<String>,
                                        selectedOption: MutableState<String>, birthDate: MutableState<String>,
                                        scholarSelected: MutableState<String>) {
    var name1 = name
    var lastName1 = lastName

    var genderOptions1 = genderOptions
    val selectedOption1 = selectedOption

    val birthDate1 = birthDate

    val scholarSelected1 = scholarSelected

    ConstraintLayout {
        val (form) = createRefs()
        Column(modifier = Modifier.constrainAs(form) {
            top.linkTo(parent.top)
        }) {

            TopSection()

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                DataTextField(
                    text = name1,
                    label = stringResource(R.string.names),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Text,
                    required = true,
                    onTextChange = { name1 = it })
                Spacer(modifier = Modifier.height(15.dp))
                DataTextField(
                    text = lastName1,
                    label = stringResource(R.string.last_names),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardType = KeyboardType.Text,
                    required = true,
                    onTextChange = { lastName1 = it })
                Spacer(modifier = Modifier.height(15.dp))
                GenderButton(genderOptions = genderOptions1, selectedOption = selectedOption1)
                Spacer(modifier = Modifier.height(15.dp))
                BirthDatePicker(currentDate = birthDate1)
                Spacer(modifier = Modifier.height(15.dp))
                ScholarGradeSpinner(
                    selectedItem = scholarSelected1,
                    onItemSelected = {selected -> scholarSelected1.value = selected
                    })
            }

        }

    }
}

//Titulo del formulario
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
                contentDescription = stringResource(R.string.screen_name),
                tint = uiColor
            )
            Spacer(modifier = Modifier.size(20.dp))
            Column {
                Text(
                    text = stringResource(R.string.screen_name),
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

//Objeto composable para la eleccion de genero
@Composable
fun GenderButton (genderOptions: List<String>, selectedOption: MutableState<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.sex))
        genderOptions.forEach { text ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = text == selectedOption.value, onClick = { selectedOption.value = text })
                Text(
                    text = text,
                    color = Color.Gray,
                    modifier = Modifier.clickable { selectedOption.value = text })
            }
        }
    }
}


//Objeto COmposable para la eleccion de fecha
@Composable
fun BirthDatePicker(currentDate: MutableState<String>) {

    val contextDate = LocalContext.current

    val selectedYear: Int
    val selectedMonth: Int
    val selectedDay: Int

    val selectedCalendar = Calendar.getInstance()

    selectedYear = selectedCalendar.get(Calendar.YEAR)
    selectedMonth = selectedCalendar.get(Calendar.MONTH)
    selectedDay = selectedCalendar.get(Calendar.DAY_OF_MONTH)

    selectedCalendar.time = Date()

    val datePicker = DatePickerDialog(
        contextDate,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            currentDate.value = "$mDayOfMonth/${if ((mMonth + 1) < 10) "0" + (mMonth + 1) else (mMonth + 1)}/$mYear"
        }, selectedYear, selectedMonth, selectedDay
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
    ) {
        Text(text = currentDate.value)
        Spacer(modifier = Modifier.width(10.dp))
        DataButton(onClick = {datePicker.show()},
            text = stringResource(R.string.change)
        )

    }
}

//Objeto compsable para la eleccion de grado de escolaridad
@Composable
fun ScholarGradeSpinner(
    selectedItem: MutableState<String>,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        stringResource(R.string.elementary),
        stringResource(R.string.high_school),
        stringResource(R.string.university),
        stringResource(R.string.other)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .width(250.dp)
        ) {
            Text(
                text = selectedItem.value,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .border(2.dp, Color.Gray)
                    .padding(16.dp)
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                items.forEach { label ->
                    DropdownMenuItem(onClick = {
                        onItemSelected(label)
                        expanded = false
                    }, text = { Text(text = label) })
                }
            }
        }
    }
}




