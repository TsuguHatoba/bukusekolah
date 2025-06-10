// file: AppNavigation.kt
package com.example.bukusekolah

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bukusekolah.ui.screens.BookListScreen
import com.example.bukusekolah.ui.screens.PdfViewerScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "book_list") {
        // Rute untuk layar daftar buku
        composable("book_list") {
            BookListScreen(navController = navController)
        }
        // Rute untuk layar viewer PDF, dengan argumen nama buku
        composable(
            route = "pdf_viewer/{bookName}",
            arguments = listOf(navArgument("bookName") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookName = backStackEntry.arguments?.getString("bookName")
            if (bookName != null) {
                PdfViewerScreen(bookName = bookName)
            }
        }
    }
}