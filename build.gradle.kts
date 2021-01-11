// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  repositories {
    jcenter()
    google()
  }
  dependencies {
    classpath(BuildPlugins.TOOLS_BUILD_GRADLE)
    classpath(BuildPlugins.SAFE_ARGS_GRADLE_PLUGIN)
    classpath(kotlin("gradle-plugin", version = Versions.RootLibVersion.KOTLIN))
    classpath(kotlin("serialization", version = Versions.RootLibVersion.KOTLIN))
    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

allprojects {
  repositories {
    google()
    jcenter()
    mavenCentral()
  }
}

tasks.register("clean")
    .configure {
      delete(rootProject.buildDir)
    }
