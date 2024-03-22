package co.edu.udea.compumovil.gr07_20241.lab1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.udea.compumovil.gr07_20241.lab1.ContactData
import co.edu.udea.compumovil.gr07_20241.lab1.PersonalData

//Clase hecha para la navegacion a traves de la aplicacion
@Composable
fun Navigation() {
    //Controlador de navegacion
    val controller = rememberNavController()
    //Jerarquia propia de COmpose que permite la navegacion
    //Los parametro son navController, siendo el controlador para el host de navegacion
    //startDestination
    NavHost(navController = controller, startDestination = Screens.PersonalData.route) {
        //Adiciona el componente composable a la ruta de navegacion
        composable(route = Screens.PersonalData.route) {
            PersonalData(controller)
        }
        composable(route = Screens.ContactData.route) {
            ContactData(controller)
        }
    }
}