plugins {
  id("java-library")
  id("org.jetbrains.kotlin.jvm")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  api(Lib.Kotlin.KT_STD)
  api(Lib.Kotlin.COROUTINES)
  implementation(Lib.Paging.PAGING_COMMON)
}