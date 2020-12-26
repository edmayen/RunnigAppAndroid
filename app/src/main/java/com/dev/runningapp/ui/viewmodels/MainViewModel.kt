package com.dev.runningapp.ui.viewmodels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.runningapp.db.Run
import com.dev.runningapp.other.SortType
import com.dev.runningapp.repositories.MainRepository
import kotlinx.coroutines.launch


class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    private val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
    private val runsSortedByDistance = mainRepository.getAllRunsSortedByDistance()
    private val runsSortedByCaloriesBurned = mainRepository.getAllRunsSortedByCaloriesBurned()
    private val runsSortedByTimeInMillis = mainRepository.getAllRunsSortedByTimeInMillis()
    private val runsSortedAvgSpeed = mainRepository.getAllRunsSortedByAvgSpeed()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init{
        runs.addSource(runsSortedByDate){result ->
            if (sortType == SortType.DATE){
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedAvgSpeed){result ->
            if (sortType == SortType.AVG_SPEED){
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByCaloriesBurned){result ->
            if (sortType == SortType.CALORIES_BURNED){
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByDistance){result ->
            if (sortType == SortType.DISTANCE){
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runsSortedByTimeInMillis){result ->
            if (sortType == SortType.RUNNING_TIME){
                result?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType){
        SortType.DATE -> runsSortedByDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runsSortedByTimeInMillis.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runsSortedAvgSpeed.value?.let { runs.value = it }
        SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
}