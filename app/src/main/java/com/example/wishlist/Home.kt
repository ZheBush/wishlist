package com.example.wishlist

import android.adservices.topics.Topic
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlist.data.Item
import com.example.wishlist.ui.theme.Blue133
import com.example.wishlist.ui.theme.Gray235
import com.example.wishlist.ui.theme.Gray25
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

@OptIn(ExperimentalMaterial3Api::class)
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
        Column(
            verticalArrangement = Arrangement.Top
        ) {
            val textWidth = remember {
                mutableIntStateOf(0)
            }
            val config = LocalConfiguration.current
            TopAppBar(
                title = {
                    Text(
                        "eWist",
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned {
                                textWidth.intValue = it.size.width
                            }
                            .offset {
                                IntOffset(
                                    x = (config.screenWidthDp - textWidth.intValue / 2),
                                    y = 0
                                )
                            }
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = "menu"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Gray235,
                    titleContentColor = Blue133,
                    navigationIconContentColor = Gray25
                )
            )
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
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Activity()
}