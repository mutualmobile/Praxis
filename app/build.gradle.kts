plugins {
  id("AppModule")
}

// Required for annotation processing plugins like Dagger
kapt {
  generateStubs = true
  correctErrorTypes = true
}

hilt {
  // The Hilt configuration option 'enableTransformForLocalTests'
  // is no longer necessary when com.android.tools.build:gradle:4.2.0+ is used.
  // enableTransformForLocalTests = true
  enableAggregatingTask = true

  // see
  // https://github.com/google/dagger/issues/1991
  // https://github.com/google/dagger/issues/970
  enableExperimentalClasspathAggregation = true
}

dependencies {
  testApi(Lib.Room.testing)
  UnitTesting.list.forEach(::testApi)
  DevDependencies.debugList.forEach(::debugApi)
  DevDependencies.list.forEach(::api)
}
