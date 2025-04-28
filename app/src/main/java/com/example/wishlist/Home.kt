package com.example.wishlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
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
import com.example.wishlist.ui.theme.Transparent
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
        if (!isAddCardWindowOpen.value) {
            AddItemWindow(isAddCardWindowOpen, list)
        }
    }
}

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
    val id = if (list.isNotEmpty()) list.size else 0
    val title = remember {
        mutableStateOf("")
    }
    val desc = remember {
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
                    cursorColor = Blue133,
                )
            )
            val isSwitchEnabled = remember {
                mutableStateOf(false)
            }
            TextField(
                value = "Description",
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
                    cursorColor = Blue133,
                )
            )
            Row(
                modifier = Modifier.padding(22.dp, 16.dp)
            ) {
                CustomSwitch(
                    width = 22.dp,
                    height = 13.dp,
                    strokeWith = 1.dp,
                    gapBetweenThumbAndTrackEdge = 2.dp,
                    checkedTrackColor = Gray235,
                    uncheckedTrackColor = Gray180,
                    defaultText = "Private mode ",
                    textOn = "on",
                    textOff = "off",
                    onCheckedChange = {
                        isPrivate.value = !isPrivate.value
                    }
                )
            }
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
                                desc.value,
                                isPrivate.value
                            )
                        )
                        isOpen.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue133,
                        contentColor = Gray235
                    )
                ) {
                    Text("Create", fontFamily = FontFamily.Monospace)
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

@OptIn(ExperimentalAnimationApi::class)
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
    defaultText: String = "",
    textOn: String = "",
    textOff: String = "",
    switchOn: MutableState<Boolean> = remember {
        mutableStateOf(false)
    },
    onCheckedChange: (Boolean) -> Unit
) {
    val animationSpec = 250
    val animatePosition = animateFloatAsState(
        targetValue = if (switchOn.value)
            with(LocalDensity.current) {
                (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx()
            }
        else
            with(LocalDensity.current) {
                (thumbRadius + gapBetweenThumbAndTrackEdge).toPx()
            },
        animationSpec = tween(animationSpec),
        label = ""
    )
    val animatedSwitchText = animateFloatAsState(
        targetValue = if (switchOn.value) 1f else 0f,
        animationSpec = tween(animationSpec),
        label = ""
    )
    val background = remember {
        derivedStateOf {
            if (switchOn.value) Blue133 else Gray235
        }
    }
    val switchTextState = remember {
        mutableStateOf(textOff)
    }
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(width = width, height = height)
                .scale(scale)
                .pointerInput(Unit) {
                    detectTapGestures {
                        switchOn.value = !switchOn.value
                        switchTextState.value =
                            if ((switchTextState.value) == textOff) textOn else textOff
                    }
                }
                .background(background.value, shape = RoundedCornerShape(10.dp))
        ) {
            drawRoundRect(
                color = if (switchOn.value) checkedTrackColor else uncheckedTrackColor,
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
        Spacer(modifier = Modifier.size(20.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = defaultText,
                fontFamily = FontFamily.Monospace
            )
            Box(
                modifier = Modifier
                    .clipToBounds()
            ) {
                Text(
                    text = textOff,
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = 30.dp.toPx() * animatedSwitchText.value
                        },
                    color = Gray25,
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = textOn,
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = -30.dp.toPx() * (1f - animatedSwitchText.value)
                        },
                    color = Gray25,
                    fontFamily = FontFamily.Monospace
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Activity()
}