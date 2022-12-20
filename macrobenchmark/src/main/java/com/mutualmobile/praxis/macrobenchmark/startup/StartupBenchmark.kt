package com.mutualmobile.praxis.macrobenchmark.startup

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mutualmobile.praxis.macrobenchmark.PACKAGE_NAME
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */

@RunWith(AndroidJUnit4::class)
class StartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = PACKAGE_NAME,
        metrics = listOf(StartupTimingMetric()),
        iterations = 3,
        startupMode = StartupMode.HOT
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun warmStartup() = benchmarkRule.measureRepeated(
        packageName = PACKAGE_NAME,
        metrics = listOf(StartupTimingMetric(), FrameTimingMetric()),
        iterations = 2,
        startupMode = StartupMode.WARM
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun hotStartup() = benchmarkRule.measureRepeated(
        packageName = PACKAGE_NAME,
        metrics = listOf(StartupTimingMetric()),
        iterations = 3,
        startupMode = StartupMode.HOT
    ) {
        pressHome()
        startActivityAndWait()
    }
}
