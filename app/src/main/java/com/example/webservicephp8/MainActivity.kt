package com.example.webservicephp8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.webservicephp8.ui.theme.WebServicePHP8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WebServicePHP8Theme {
                var currentScreen by remember { mutableStateOf("add") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Add, contentDescription = "Ajouter") },
                                label = { Text("Ajouter") },
                                selected = currentScreen == "add",
                                onClick = { currentScreen = "add" }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.List, contentDescription = "Liste") },
                                label = { Text("Liste") },
                                selected = currentScreen == "list",
                                onClick = { currentScreen = "list" }
                            )
                        }
                    }
                ) { innerPadding ->
                    if (currentScreen == "add") {
                        AddEtudiantScreen(modifier = Modifier.padding(innerPadding))
                    } else {
                        ListEtudiantScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}
