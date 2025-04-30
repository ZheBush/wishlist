package com.example.wishlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wishlist.data.Item
import com.example.wishlist.ui.theme.Blue133
import com.example.wishlist.ui.theme.Gray235
import com.example.wishlist.ui.theme.White

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Activity()
        }
    }
}

@Composable
fun Activity() {
    val list = remember {
        mutableStateListOf(
            Item(
                0, "ABC", "Description", false
            ),
        )
    }
    val isAddCardWindowOpen = remember {
        mutableStateOf(false)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isAddCardWindowOpen.value = true
                },
                shape = CircleShape,
                modifier = Modifier
                    .padding(20.dp)
                    .shadow(5.dp, CircleShape),
                containerColor = White,
                contentColor = Blue133
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "add item",
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        containerColor = Gray235
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(15.dp)
        ) {
            itemsIndexed(list) { _, item ->
                OneCard(item = item, list = list)
            }
        }
        if (isAddCardWindowOpen.value) {
            AddItemWindow(isAddCardWindowOpen, list)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Activity()
}