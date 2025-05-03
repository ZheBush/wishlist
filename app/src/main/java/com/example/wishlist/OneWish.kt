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
import com.example.wishlist.itemActions.ChangeItemWindow
import com.example.wishlist.itemActions.DeleteItemWindow
import com.example.wishlist.ui.theme.Blue133
import com.example.wishlist.ui.theme.White

@Composable
fun OneWish(item: Item, list: SnapshotStateList<Item>) {
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
        val isChangeItemWindowOpen = remember {
            mutableStateOf(false)
        }
        Text(
            item.title,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(5.dp)
                .weight(1f)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    isChangeItemWindowOpen.value = true
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
            if (isChangeItemWindowOpen.value) {
                ChangeItemWindow(isChangeItemWindowOpen, item, list)
            }
        }
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(8.dp))
}

@Preview(showBackground = false)
@Composable
fun OneWishPreview() {
    OneWish(
        item = Item(
            0,
            "ABC",
            "DESC"
        ),
        list = remember {
            mutableStateListOf()
        }
    )
}