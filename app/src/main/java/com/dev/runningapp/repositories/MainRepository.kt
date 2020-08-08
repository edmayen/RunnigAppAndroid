package com.dev.runningapp.repositories

import com.dev.runningapp.db.Run
import com.dev.runningapp.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDao
) {

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunSortedByDate()

    fun getAllRunsSortedByDistance() = runDao.getAllRunSortedByDistanceInMeters()

    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunSortedByTimeMillis()

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunSortedByAvgSpeedInKMH()

    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunSortedByCaloriesBurned()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistanceInMeters()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()

}