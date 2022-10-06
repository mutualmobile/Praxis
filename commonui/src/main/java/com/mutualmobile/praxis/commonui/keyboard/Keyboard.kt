package com.mutualmobile.praxis.commonui.keyboard

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView

@Immutable
sealed class Keyboard {
  @Immutable data class Opened(val height: Int) : Keyboard()
  @Immutable object Closed : Keyboard()
}

@Composable
fun keyboardAsState(): State<Keyboard> {
  val keyboardState = remember { mutableStateOf<Keyboard>(Keyboard.Closed) }
  val view = LocalView.current
  DisposableEffect(view) {
    val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
      val rect = Rect()
      view.getWindowVisibleDisplayFrame(rect)
      val screenHeight = view.rootView.height
      val keypadHeight = screenHeight - rect.bottom
      keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
        Keyboard.Opened(screenHeight - keypadHeight)
      } else {
        Keyboard.Closed
      }
    }
    view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

    onDispose {
      view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
    }
  }

  return keyboardState
}