package com.mutualmobile.praxis.data.repositories

import android.content.Context
import com.mutualmobile.praxis.domain.repositories.RandomFileService
import java.io.File
import javax.inject.Inject

class RandomFileServiceImpl @Inject constructor(private val context: Context) : RandomFileService {
  override fun getTempFile(): File {
    return File(context.filesDir, "random")
  }
}