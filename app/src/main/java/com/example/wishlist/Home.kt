package com.example.wishlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.wishlist.data.Item
import com.example.wishlist.ui.theme.Blue133
import com.example.wishlist.ui.theme.Gray180
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

@Composable
fun Activity() {
    val list = remember {
        mutableStateListOf(
            Item(
                1, "ABC", false
            ),
            Item(
                2, "DEF", true
            )
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
        if (!isAddCardWindowOpen.value) {
            AddItemWindow(isAddCardWindowOpen, list)
        }
    }
}

@Composable
fun OneCard(item: Item, list: SnapshotStateList<Item>) {
    Row(
        modifier = Modifier
            .background(White, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(55.dp)
            .shadow(1.dp, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
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
                    list.remove(item)
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
        }
    }
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(8.dp))
}

@Composable
fun AddItemWindow(isOpen: MutableState<Boolean>, list: SnapshotStateList<Item>) {
    val id = when {
        (list.isNotEmpty()) -> list.last().id
        else -> 0
    }
    val title = remember {
        mutableStateOf("")
    }
    val isPrivate = remember {
        mutableStateOf(false)
    }
    Dialog(
        onDismissRequest = {
            isOpen.value = false
        }
    ) {
        Column(
            modifier = Modifier
                .background(Gray235, RoundedCornerShape(5.dp))
                .size(300.dp, 500.dp)
        ) {
            TextField(
                value = "Title",
                onValueChange = {newText -> title.value = newText},
                textStyle = TextStyle(
                    fontSize = 16.sp,
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
                    cursorColor = Blue133,
                )
            )
            val isSwitchEnabled = remember {
                mutableStateOf(false)
            }
            Row(
                modifier = Modifier.padding(22.dp, 16.dp)
            ){
                CustomSwitch(
                    width = 24.dp,
                    height = 14.dp,
                    strokeWith = 1.dp,
                    gapBetweenThumbAndTrackEdge = 2.dp
                )
            }
        }
    }
}

@Composable
fun CustomSwitch(
    scale: Float = 2f,
    width: Dp,
    height: Dp,
    thumbRadius: Dp = height / 3,
    strokeWith: Dp = 2.dp,
    uncheckedThumbColor: Color = Blue133,
    uncheckedTrackColor: Color = Gray235,
    uncheckedBorderColor: Color = Gray25,
    checkedThumbColor: Color = Gray235,
    checkedTrackColor: Color = Blue133,
    checkedBorderColor: Color = Blue133,
    gapBetweenThumbAndTrackEdge: Dp = 4.dp,
    switchOn: MutableState<Boolean> = mutableStateOf(false)
) {
    val animatePosition = animateFloatAsState(
        targetValue = if (switchOn.value)
            with(LocalDensity.current) {
                (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx()
            }
        else
            with(LocalDensity.current) {
                (thumbRadius + gapBetweenThumbAndTrackEdge).toPx()
            },
        label = ""
    )
    val background = remember {
        mutableStateOf(Gray235)
    }
    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale)
            .pointerInput(Unit) {
                detectTapGestures {
                    switchOn.value = !switchOn.value
                    background.value = if (background.value == Gray235) Blue133 else Gray235
                }
            }
            .background(background.value, shape = RoundedCornerShape(10.dp))
    ) {
        drawRoundRect(
            color = if (switchOn.value) checkedBorderColor else uncheckedBorderColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = strokeWith.toPx())
        )
        drawCircle(
            color = if (switchOn.value) checkedThumbColor else uncheckedThumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Activity()
}