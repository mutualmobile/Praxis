package com.praxis.feat.authentication.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import com.google.android.material.snackbar.Snackbar
import com.mutualmobile.praxis.domain.model.StreamingFile
import com.praxis.feat.authentication.R
import com.praxis.feat.authentication.databinding.ViewRandomPhotosBinding
import com.praxis.feat.authentication.vm.RandomViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.FileNotFoundException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private val viewModel: RandomViewModel by viewModels()
  private lateinit var binding: ViewRandomPhotosBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    binding = ViewRandomPhotosBinding.inflate(LayoutInflater.from(this))
    setContentView(binding.root)
    viewModel.randomViewState.observe(this) { randomPhotoViewState ->
      randomPhotoViewState?.let {
        receiveViewState(randomPhotoViewState)
      }
    }
    binding.randomPhotoButton.setOnClickListener {
      viewModel.fetchRandomPhoto()
    }
  }

  private fun receiveViewState(randomPhotoViewState: RandomViewModel.RandomPhotoViewState) {
    when (randomPhotoViewState) {
      is RandomViewModel.RandomPhotoViewState.Empty -> {
        Timber.e("No Data")
        setImageForFile(randomPhotoViewState.result)
      }
      is RandomViewModel.RandomPhotoViewState.Exception -> {
        randomPhotoViewState.throwable.printStackTrace()
        Timber.e(randomPhotoViewState.throwable.message ?: "")
        when (randomPhotoViewState.throwable) {
          is FileNotFoundException -> {
            showSnackbarMessage(getString(R.string.except_file_not_found))
          }
          is CancellationException -> {
            when (randomPhotoViewState.throwable.cause) {
              is UnknownHostException -> {
                showSnackbarMessage(getString(R.string.except_no_network))
              }
              else -> {
                showSnackbarMessage(getString(R.string.except_generic))
              }
            }
          }
          else -> {
            showSnackbarMessage(randomPhotoViewState.throwable.message ?: "")
          }
        }
      }
      is RandomViewModel.RandomPhotoViewState.Streaming -> {
        binding.progressText.visibility = View.VISIBLE

        if (randomPhotoViewState.result.isComplete) {
          setImageForFile(randomPhotoViewState.result)
        } else {
          binding.progressText.text =
            getString(R.string.downloading_glue, randomPhotoViewState.result.progress)
        }
        Timber.d(
          "${randomPhotoViewState.result.file.absolutePath} ${randomPhotoViewState.result.file.length()}"
        )
      }
    }
  }

  private fun setImageForFile(file: StreamingFile?) {
    binding.photoView.setImageResource(android.R.drawable.gallery_thumb) // to avoid redraw issues
    file?.let { streamingFile ->
      binding.photoView.setImageURI(Uri.fromFile(streamingFile.file))
      binding.progressText.visibility = View.VISIBLE
      binding.progressText.text =
        getString(R.string.downloaded_glue, streamingFile.file.length())
    }
  }

  private fun showSnackbarMessage(message: String) {
    Snackbar.make(binding.root, message, LENGTH_SHORT).show()
  }
}