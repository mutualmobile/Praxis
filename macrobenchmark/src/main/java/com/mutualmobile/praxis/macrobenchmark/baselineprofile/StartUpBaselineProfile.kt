package com.mutualmobile.praxis.macrobenchmark.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import com.mutualmobile.praxis.macrobenchmark.PACKAGE_NAME
import org.junit.Rule
import org.junit.Test

class StartUpBaselineProfile {
    @get:Rule
    val baselineProfile = BaselineProfileRule()

    @Test
    fun startUp() = baselineProfile.collectBaselineProfile(
        PACKAGE_NAME,
        profileBlock = {
            startActivityAndWait()
        }
    )
}