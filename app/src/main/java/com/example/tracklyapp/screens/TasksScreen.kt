package com.example.tracklyapp.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tracklyapp.data.entity.Card
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tracklyapp.ui.theme.Purple80

@Composable
fun TasksScreen(
    onDeleteClick: (Card) -> Unit,
    onMarkClick: (Card) -> Unit,
    cardList: List<Card>,
    onAddClick: () -> Unit,
    onHistoryClick: (Card) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onAddClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Добавить карточку",
                    tint = Purple80,
                    modifier = Modifier.size(48.dp)
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )
    { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(cardList) { item ->
                CardItem(item, onDeleteClick, onMarkClick, onHistoryClick)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(
    card: Card,
    onDeleteClick: (Card) -> Unit,
    onMarkClick: (Card) -> Unit,
    onHistoryClick: (Card) -> Unit
) {
    var showSheet by remember { mutableStateOf(false) }
    if (showSheet) {
        ModalBottomSheet(onDismissRequest = { showSheet = false }) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { onDeleteClick(card) }) {
                    Text("Delete")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { onHistoryClick(card) }) {
                    Text("History")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { onMarkClick(card) }) {
                    Text("+Mark")
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showSheet = true },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = card.name, fontSize = 32.sp)
                Text(text = "Цель:${card.timeScore} минут.", fontSize = 32.sp)
            }
        }
    }
}