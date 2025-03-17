package com.raiffeisen.peopleagenda.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raiffeisen.peopleagenda.presentation.agenda.AgendaScreen
import com.raiffeisen.peopleagenda.ui.theme.PeopleAgendaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PeopleAgendaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AgendaOverview
                    ) {
                        composable<AgendaOverview> {
                            AgendaScreen()
                        }
                    }
                }
            }
        }
    }
}