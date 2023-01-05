plugins {
  id("DataModule")
}

// Required for annotation processing plugins like Dagger
kapt {
  generateStubs = true
  correctErrorTypes = true
}

dependencies {
  UnitTesting.list.forEach(::testImplementation)
  DevDependencies.debugList.forEach(::debugImplementation)
  DevDependencies.list.forEach(::implementation)
}