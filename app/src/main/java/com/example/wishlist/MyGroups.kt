package com.example.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlist.data.Item
import com.example.wishlist.ui.theme.Blue104
import com.example.wishlist.ui.theme.Gray235
import com.example.wishlist.ui.theme.Gray25
import com.example.wishlist.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyGroups(navController: NavController) {
    val textWidth = remember {
        mutableIntStateOf(0)
    }
    val config = LocalConfiguration.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val selectedItem = remember {
        mutableStateOf("MY GROUPS")
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Gray235,
                drawerContentColor = Blue104,
                modifier = Modifier
                    .requiredWidth(300.dp)
            ) {
                listOf(
                    "MY LIST",
                    "MY GROUPS",
                    "RESERVED",
                    "SETTINGS"
                ).forEach { item ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = item,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace,
                            )
                        },
                        selected = selectedItem.value == item,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                                selectedItem.value = item
                            }
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Blue104,
                            unselectedContainerColor = Gray235,
                            selectedTextColor = Gray235,
                            unselectedTextColor = Gray25
                        )
                    )
                    TextButton(
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    ) {}
                }
            }
        },
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "MY GROUPS",
                            fontSize = 18.sp,
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
                                scope.launch {
                                    if (drawerState.isOpen) {
                                        drawerState.close()
                                    }
                                    else {
                                        drawerState.open()
                                    }
                                }
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
                        titleContentColor = Blue104,
                        navigationIconContentColor = Gray25
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {

                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(20.dp)
                        .shadow(5.dp, CircleShape),
                    containerColor = White,
                    contentColor = Blue104
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
        ) { innerPadding ->
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentPadding = PaddingValues(
                        top = 0.dp,
                        bottom = 15.dp,
                        start = 15.dp,
                        end = 15.dp
                    )
                ) {}
            }
        }
    }
}
