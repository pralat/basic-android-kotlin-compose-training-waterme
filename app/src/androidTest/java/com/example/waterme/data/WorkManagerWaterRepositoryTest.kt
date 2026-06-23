package com.example.waterme.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.WorkManager
import androidx.work.testing.WorkManagerTestInitHelper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class WorkManagerWaterRepositoryTest {
    private lateinit var context: Context
    private lateinit var workManager: WorkManager
    private lateinit var repository: WorkManagerWaterRepository

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        WorkManagerTestInitHelper.initializeTestWorkManager(context)
        workManager = WorkManager.getInstance(context)
        repository = WorkManagerWaterRepository(context)
    }

    @Test
    fun scheduleReminder_enqueuesCorrectWork() {
        val plantName = "Carrot"
        val duration = 5L
        val unit = TimeUnit.SECONDS
        
        repository.scheduleReminder(duration, unit, plantName)

        // The unique work name is plantName + duration
        val workInfos = workManager.getWorkInfosForUniqueWork(plantName + duration).get()
        
        assertEquals(1, workInfos.size)
        val workInfo = workInfos[0]
        
        // Verify it is enqueued
        assertEquals(androidx.work.WorkInfo.State.ENQUEUED, workInfo.state)
    }
}
