package com.example.tracklyapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tracklyapp.data.entity.Activity
import com.example.tracklyapp.data.entity.Card
import com.example.tracklyapp.screens.MarksEditScreen
import com.example.tracklyapp.screens.MarksScreen
import com.example.tracklyapp.screens.TasksEditScreen
import com.example.tracklyapp.screens.TasksScreen
import com.example.tracklyapp.state.UiState
import com.example.tracklyapp.viewmodel.TracklyViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(viewModel: TracklyViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCards()
    }
    Scaffold(modifier = Modifier.fillMaxSize())
    { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = TASKS_SCREEN,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(TASKS_SCREEN) {
                when (state) {
                    is UiState.Error -> Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Error,
                            contentDescription = "Ошибка",
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = (state as UiState.Error).message,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    is UiState.Loading -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.WbSunny,
                                contentDescription = "Ошибка",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Text(
                                text = "Загрузка...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LinearProgressIndicator(
                                modifier = Modifier.width(200.dp)
                            )
                        }
                    }

                    is UiState.Success -> {
                        val cards = (state as UiState.Success).cards
                        LaunchedEffect(cards) {
                            viewModel.loadTotalDuration(cards)
                        }
                        val durations by viewModel.duration.collectAsState()
                        TasksScreen(
                            cardList = (state as UiState.Success).cards,
                            onDeleteClick = { card -> viewModel.deleteCard(card) },
                            onMarkClick = { card -> navController.navigate("$MARKS_EDIT_SCREEN/${card.id}") },
                            onAddClick = { navController.navigate(TASKS_EDIT_SCREEN) },
                            onHistoryClick = { card -> navController.navigate("$MARKS_SCREEN/${card.id}") },
                            durations = durations
                        )
                    }
                }
            }
            composable(
                route = "$MARKS_SCREEN/{cardId}",
                arguments = listOf(navArgument("cardId") { type = NavType.IntType })
            ) { backStackEntry ->
                val cardId = backStackEntry.arguments?.getInt("cardId") ?: 0
                val activities by viewModel.activities.collectAsState()

                LaunchedEffect(cardId) {
                    viewModel.getActivities(cardId)
                }

                MarksScreen(
                    activityList = activities,
                    onBackClick = { navController.popBackStack() })
            }
            composable(TASKS_EDIT_SCREEN) {
                TasksEditScreen { name, time ->
                    viewModel.addCard(Card(id = 0, name = name, timeScore = time))
                    navController.popBackStack()
                }
            }
            composable(
                route = "$MARKS_EDIT_SCREEN/{cardId}",
                arguments = listOf(navArgument("cardId") { type = NavType.IntType })
            ) { backStackEntry ->
                val cardId = backStackEntry.arguments?.getInt("cardId") ?: 0
                MarksEditScreen(
                    onBackClick = { navController.popBackStack() },
                    onSaveClick = { duration, note ->
                        viewModel.addActivity(
                            Activity(
                                id = 0,
                                cardId = cardId,
                                duration = duration ?: 0,
                                note = note,
                                date = java.time.LocalDate.now().toString()
                            )
                        )
                        navController.popBackStack()
                    })
            }
        }

    }
}

private const val MARKS_SCREEN = "MarksScreen"
private const val TASKS_SCREEN = "TasksScreen"
private const val TASKS_EDIT_SCREEN = "TasksEditScreen"
private const val MARKS_EDIT_SCREEN = "MarksEditScreen"