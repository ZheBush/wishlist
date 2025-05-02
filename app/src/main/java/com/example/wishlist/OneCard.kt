package com.example.wishlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wishlist.data.Item
import com.example.wishlist.ui.theme.Blue133
import com.example.wishlist.ui.theme.White

@Composable
fun OneCard(item: Item, list: SnapshotStateList<Item>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .background(White, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val isDeleteItemWindowOpen = remember {
            mutableStateOf(false)
        }
        Text(
            item.title,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier.padding(5.dp),
            overflow = TextOverflow.Ellipsis
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {

                },
                modifier = Modifier
                    .padding(5.dp, 0.dp)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = White,
                    containerColor = Blue133
                )
            ) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "edit item"
                )
            }
            Button(
                onClick = {
                    isDeleteItemWindowOpen.value = true
                },
                modifier = Modifier
                    .fillMaxHeight(),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = White,
                    containerColor = Blue133
                )
            ) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "delete item"
                )
            }
            if (isDeleteItemWindowOpen.value) {
                DeleteItemWindow(isDeleteItemWindowOpen, item, list)
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun OneCardPreview() {
    OneCard(
        item = Item(
            0,
            "ABC",
            "DESC",
            false
        ),
        list = remember {
            mutableStateListOf()
        }
    )
}