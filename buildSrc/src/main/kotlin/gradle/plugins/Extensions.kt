package gradle.plugins

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.buildscript

fun BaseExtension.configureJava() {
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}


fun Project.configureRepositories(): Project =
  this.also {
    it.buildscript {
      repositories.google()
    }
    it.repositories.google()
  }