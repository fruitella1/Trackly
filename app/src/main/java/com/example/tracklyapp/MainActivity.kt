package com.example.tracklyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tracklyapp.modules.appModule
import com.example.tracklyapp.navigation.AppNavigation
import com.example.tracklyapp.screens.TasksEditScreen
import com.example.tracklyapp.screens.TasksScreen
import com.example.tracklyapp.ui.theme.TracklyAppTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin()
        enableEdgeToEdge()
        setContent {
            TracklyAppTheme {
                AppNavigation()
            }
        }
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
    }
}
