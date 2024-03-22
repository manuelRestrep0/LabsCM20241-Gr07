package co.edu.udea.compumovil.gr07_20241.lab1.navigation

sealed class Screens (val route: String) {
    object PersonalData: Screens("personal_data_screen")
    object ContactData: Screens("contact_data_screen")

}