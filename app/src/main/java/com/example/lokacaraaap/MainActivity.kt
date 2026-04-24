package com.example.lokacaraaap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Mengaktifkan tampilan layar penuh (Edge-to-Edge)
        enableEdgeToEdge()

        setContent {
                // 3. Panggil fungsi Navigasi Utama yang sudah dibuat di ComposeApp.kt
                LokacaraApp()
            }
        }
    }
