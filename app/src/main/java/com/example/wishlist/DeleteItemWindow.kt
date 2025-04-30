package com.example.wishlist

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.wishlist.data.Item
import com.example.wishlist.ui.theme.Blue133
import com.example.wishlist.ui.theme.Gray235


@Composable
fun DeleteItemWindow(isOpen: MutableState<Boolean>, item: Item, list: SnapshotStateList<Item>) {
    Dialog(
        onDismissRequest = {
            isOpen.value = false
        }
    ) {
        Column(
            modifier = Modifier
                .background(Gray235, RoundedCornerShape(5.dp))
                .size(300.dp, 180.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Delete this wish?",
                fontFamily = FontFamily.Monospace,
                fontSize = 18.sp,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        list.remove(item)
                        isOpen.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue133,
                        contentColor = Gray235
                    )
                ) {
                    Text("Delete", fontFamily = FontFamily.Monospace)
                }
                Button(
                    onClick = {
                        isOpen.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue133,
                        contentColor = Gray235
                    )
                ) {
                    Text("Cancel", fontFamily = FontFamily.Monospace)
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DeleteItemWindowPreview() {
    DeleteItemWindow(
        remember {
            mutableStateOf(true)
        },
        Item(
            0,
            "abc",
            "desc",
            false
        ),
        remember {
            mutableStateListOf()
        }
    )
}