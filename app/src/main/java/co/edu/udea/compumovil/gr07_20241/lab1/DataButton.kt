package co.edu.udea.compumovil.gr07_20241.lab1

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.Black
import co.edu.udea.compumovil.gr07_20241.lab1.ui.theme.BlueGray

@Composable
fun DataButton (
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSystemInDarkTheme()) BlueGray else Black,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ){
        Text(text = text)
    }

}