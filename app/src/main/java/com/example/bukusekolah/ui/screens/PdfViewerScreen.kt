// file: ui/screens/PdfViewerScreen.kt
package com.example.bukusekolah.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.barteksc.pdfviewer.PDFView

@Composable
fun PdfViewerScreen(bookName: String) {
    // Menggunakan AndroidView untuk menampilkan View tradisional di dalam Compose
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            // Blok ini dieksekusi sekali untuk membuat View
            PDFView(context, null)
        },
        update = { pdfView ->
            // Blok ini dieksekusi saat recomposition untuk mengupdate View
            pdfView.fromAsset("pdf/$bookName")
                .enableSwipe(true)
                .swipeHorizontal(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .load()
        }
    )
}