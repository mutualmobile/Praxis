package com.mutualmobile.praxis.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import com.mutualmobile.praxis.R
import com.mutualmobile.praxis.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Displaying edge-to-edge
    WindowCompat.setDecorFitsSystemWindows(window, false)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
  }
}