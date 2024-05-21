package com.esmailelhanash.remotekeyboard.data

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

suspend fun saveImageToInternalStorage(context: Context, imageUri: Uri): String? = withContext(
    Dispatchers.IO) {
        // This coroutine's context includes the Dispatchers.IO
        val inputStream = context.contentResolver.openInputStream(imageUri)
        inputStream?.use { iStream ->
            val filename = "myImage_${System.currentTimeMillis()}.jpeg"
            val outputFile = File(context.filesDir, filename)
            FileOutputStream(outputFile).use { outputStream ->
                iStream.copyTo(outputStream) // This is a simple way to copy streams
            }
            outputFile.absolutePath // You can store this path in your database
        }
    }