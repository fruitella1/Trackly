package com.example.tracklyapp.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tracklyapp.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    onDeleteClick: (Card) -> Unit,
    onMarkClick: (Card) -> Unit,
    cardList: List<Card>,
    onAddClick: () -> Unit,
    onHistoryClick: (Card) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("My activities", fontSize = 28.sp) }) }, floatingActionButton = {
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

    androidx.compose.material3.Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1e1e1e)
        ),
        border = BorderStroke(2.dp, Color.LightGray)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showSheet = true }
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center
                ) {
                    Text(text = card.name, fontSize = 24.sp, color = Color.White)

                    Text(
                        text = "Target:${card.timeScore} min.",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
    if (showSheet) {
        ModalBottomSheet(onDismissRequest = { showSheet = false }) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Button(onClick = { onDeleteClick(card) }, modifier = Modifier.fillMaxWidth()) {
                    Text("Delete")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { onHistoryClick(card) }, modifier = Modifier.fillMaxWidth()) {
                    Text("History")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { onMarkClick(card) }, modifier = Modifier.fillMaxWidth()) {
                    Text("+Mark")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}