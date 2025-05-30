package com.example.wishlist.itemActions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.wishlist.data.Item
import com.example.wishlist.ui.theme.Blue104
import com.example.wishlist.ui.theme.Gray180
import com.example.wishlist.ui.theme.Gray235
import com.example.wishlist.ui.theme.Gray25

@Composable
fun AddItemWindow(isOpen: MutableState<Boolean>, list: SnapshotStateList<Item>) {
    val id = if (list.isNotEmpty()) list.size else 0
    val title = remember {
        mutableStateOf("Title")
    }
    val desc = remember {
        mutableStateOf("Description")
    }
    Dialog(
        onDismissRequest = {
            isOpen.value = false
        }
    ) {
        Column(
            modifier = Modifier
                .background(Gray235, RoundedCornerShape(5.dp))
                .size(300.dp, 400.dp)
        ) {
            TextField(
                value = title.value,
                onValueChange = { newText -> title.value = newText },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp),
                shape = RoundedCornerShape(5.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Gray235,
                    unfocusedTextColor = Gray180,
                    focusedContainerColor = Gray235,
                    focusedTextColor = Gray25,
                    cursorColor = Blue104,
                )
            )
            TextField(
                value = desc.value,
                onValueChange = { newText -> desc.value = newText },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Monospace
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 10.dp),
                shape = RoundedCornerShape(5.dp),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Gray235,
                    unfocusedTextColor = Gray180,
                    focusedContainerColor = Gray235,
                    focusedTextColor = Gray25,
                    cursorColor = Blue104,
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        list.add(
                            Item(
                                id,
                                title.value,
                                desc.value
                            )
                        )
                        isOpen.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue104,
                        contentColor = Gray235
                    )
                ) {
                    Text("Create",  fontFamily = FontFamily.Monospace)
                }
                Button(
                    onClick = {
                        isOpen.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue104,
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
fun AddItemWindowPreview() {
    AddItemWindow(
        remember {
            mutableStateOf(true)
        },
        remember {
            mutableStateListOf()
        }
    )
}