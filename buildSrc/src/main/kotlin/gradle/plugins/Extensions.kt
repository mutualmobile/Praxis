package gradle.plugins

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.buildscript

fun JavaPluginExtension.configureJava() {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}


fun Project.configureRepositories(): Project =
  this.also {
    it.buildscript {
      repositories.google()
    }
    it.repositories.google()
  }