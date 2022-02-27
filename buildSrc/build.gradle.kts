plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
  jcenter()
  google()
  maven("https://plugins.gradle.org/m2/")
}


dependencies{
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
  implementation("com.android.tools.build:gradle:7.1.1")
  implementation("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
  implementation("org.jlleitschuh.gradle:ktlint-gradle:9.2.1")

  /* Depend on the default Gradle API's since we want to build a custom plugin */
  implementation(gradleApi())
  implementation(localGroovy())
}