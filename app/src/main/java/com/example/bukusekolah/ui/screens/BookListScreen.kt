// file: ui/screens/BookListScreen.kt
package com.example.bukusekolah.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.bukusekolah.ui.components.BookItem
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(navController: NavController) {
    val context = LocalContext.current
    // Mengambil daftar buku dari assets, 'remember' agar tidak dieksekusi ulang saat recomposition
    val bookList = remember {
        try {
            context.assets.list("pdf")?.toList() ?: emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Perpustakaan Sekolah") })
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(bookList) { bookName ->
                BookItem(bookName = bookName) {
                    // Aksi saat item di-klik: Navigasi ke layar PDF
                    navController.navigate("pdf_viewer/$bookName")
                }
            }
        }
    }
}