package com.mutualmobile.praxis.commonui.reusable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation

@Composable
fun PraxisImageBox(modifier: Modifier, imageUrl: String) {
  Image(
    painter = rememberAsyncImagePainter(
      ImageRequest.Builder(LocalContext.current).data(data = imageUrl)
        .apply(block = fun ImageRequest.Builder.() {
          transformations(RoundedCornersTransformation(12.0f, 12.0f, 12.0f, 12.0f))
        }).build()
    ),
    contentDescription = null,
    modifier = modifier
  )
}