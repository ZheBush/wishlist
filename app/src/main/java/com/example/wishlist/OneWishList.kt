package com.example.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlist.data.Item
import com.example.wishlist.data.Wishlist
import com.example.wishlist.ui.theme.Blue104
import com.example.wishlist.ui.theme.Gray235
import com.example.wishlist.ui.theme.Gray25
import com.example.wishlist.ui.theme.White

val list = Wishlist(
    0,
    "My first list",
    true,
    listOf(
        Item(
            0,
            "AAA",
            "AAAAAAAAAAA"
        ),
        Item(
            1,
            "BBB",
            "BBBBBBBBBBB"
        ),
        Item(
            2,
            "CCC",
            "CCCCCCCCCCCC"
        ),

    )
)

@Composable
fun OneWishList(list: Wishlist) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .background(Blue104, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "ABC",
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .width(290.dp)
                .background(Gray235, RoundedCornerShape(5.dp))
                .padding(
                    top = 10.dp,
                    start = 10.dp,
                    bottom = 10.dp,
                    end = 0.dp
                )
        )
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue104,
                contentColor = Gray235
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                Icons.Filled.KeyboardArrowDown,
                contentDescription = "arrow down"
            )
        }

    }
}

@Preview(showBackground = false)
@Composable
fun OneWishListPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Gray235)
            .padding(10.dp)) {
        OneWishList(list = list)
    }
}