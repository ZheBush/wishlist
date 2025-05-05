package com.example.wishlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wishlist.ui.theme.Blue104
import com.example.wishlist.ui.theme.Gray235
import com.example.wishlist.ui.theme.White

data class HomeCard(
    val title: String,
    val image: ImageBitmap
)

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
        }
    }
}

@Composable
fun Main() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) { Home(navController) }
        composable(NavRoutes.MyWishList.route) { MyWishList() }
    }
}

@Composable
fun Home(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray235),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "eWist",
            fontSize = 22.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W900,
            textAlign = TextAlign.Center,
            color = Blue104,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Box(
            modifier = Modifier.size(350.dp)
        ) {
            val cards = listOf(
                HomeCard(
                    "MY LIST",
                    ImageBitmap.imageResource(id = R.drawable.list)
                ),
                HomeCard(
                    "RESERVED",
                    ImageBitmap.imageResource(id = R.drawable.reserved)
                ),
                HomeCard(
                    "MY GROUPS",
                    ImageBitmap.imageResource(id = R.drawable.group)
                ),
                HomeCard(
                    "SETTINGS",
                    ImageBitmap.imageResource(id = R.drawable.setting)
                ),
                )
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                items(cards) { card ->
                    Card(
                        modifier = Modifier
                            .size(150.dp)
                            .padding(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        ),
                        onClick = {
                            when(card.title) {
                                "MY LIST" -> navController.navigate(
                                    NavRoutes.MyWishList.route
                                )
                                "MY GROUPS" -> navController.navigate(
                                    NavRoutes.MyGroups.route
                                )
                                "RESERVED" -> navController.navigate(
                                    NavRoutes.Reserved.route
                                )
                                "SETTINGS" -> navController.navigate(
                                    NavRoutes.Settings.route
                                )
                            }
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Image(
                                painter = BitmapPainter(card.image),
                                contentDescription = card.title,
                                contentScale = ContentScale.Inside,
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(10.dp)
                            )
                            Text(
                                card.title,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Main()
}