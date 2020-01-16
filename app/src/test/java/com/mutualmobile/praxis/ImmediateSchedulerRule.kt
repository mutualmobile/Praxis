package com.mutualmobile.praxis

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.rules.ExternalResource
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class ImmediateSchedulersRule : ExternalResource() {

  val immediateScheduler: Scheduler = object : Scheduler() {

    override fun createWorker() = ExecutorScheduler.ExecutorWorker(Executor { it.run() },false)

    // This prevents errors when scheduling a delay
    override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
      return super.scheduleDirect(run, 0, unit)
    }

  }

  override fun before() {
    RxJavaPlugins.setIoSchedulerHandler { immediateScheduler }
    RxJavaPlugins.setComputationSchedulerHandler { immediateScheduler }
    RxJavaPlugins.setNewThreadSchedulerHandler { immediateScheduler }

    RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediateScheduler }
    RxAndroidPlugins.setMainThreadSchedulerHandler { immediateScheduler }
  }

  override fun after() {
    RxJavaPlugins.reset()
  }

}