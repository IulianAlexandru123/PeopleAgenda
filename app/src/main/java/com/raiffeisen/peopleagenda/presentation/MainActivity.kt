package com.raiffeisen.peopleagenda.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raiffeisen.peopleagenda.presentation.agenda.UsersAgendaScreen
import com.raiffeisen.peopleagenda.presentation.ui.theme.PeopleAgendaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PeopleAgendaTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            shape = FloatingActionButtonDefaults.largeShape,
                            onClick = {},
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Create,
                                contentDescription = null,
                            )
                        }
                    },
                    topBar = {
                        TopAppBar(
                            title = { Text("Users") },
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Filled.Menu,
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                        contentDescription = null,
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            ),
                            actions = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Filled.Search,
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                        contentDescription = null,
                                    )
                                }
                            },
                        )
                    }
                ) { innerPadding ->
                    val navController = rememberNavController()

                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = UsersAgendaOverview
                        ) {
                            composable<UsersAgendaOverview> {
                                UsersAgendaScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}