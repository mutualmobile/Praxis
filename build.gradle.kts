// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  repositories {
    google()
    maven("https://plugins.gradle.org/m2/")
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}

tasks.register("clean")
    .configure {
      delete(rootProject.buildDir)
    }

apply(from = teamPropsFile("git-hooks.gradle.kts"))

fun teamPropsFile(propsFile: String): File {
  val teamPropsDir = file("team-props")
  return File(teamPropsDir, propsFile)
}