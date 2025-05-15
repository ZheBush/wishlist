package com.example.wishlist

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.wishlist.ui.theme.Blue104
import com.example.wishlist.ui.theme.Gray180
import com.example.wishlist.ui.theme.Gray235
import com.example.wishlist.ui.theme.Gray25

@Composable
fun CustomSwitch(
    scale: Float = 2f,
    width: Dp = 22.dp,
    height: Dp = 12.dp,
    thumbRadius: Dp = height / 3,
    strokeWith: Dp = 1.dp,
    uncheckedThumbColor: Color = Blue104,
    uncheckedTrackColor: Color = Gray180,
    uncheckedBorderColor: Color = Gray25,
    checkedThumbColor: Color = Gray235,
    checkedTrackColor: Color = Gray235,
    checkedBorderColor: Color = Blue104,
    gapBetweenThumbAndTrackEdge: Dp = 2.dp,
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
            if (switchOn.value) Blue104 else Gray235
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