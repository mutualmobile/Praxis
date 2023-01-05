// Root module
include(":app")
includeBuild("dependencies")

// Feature modules
include(":ui-onboarding")
include(":ui-authentication")

// Other modules
include(":domain")
include(":data")
include(":common")
include(":navigator")

