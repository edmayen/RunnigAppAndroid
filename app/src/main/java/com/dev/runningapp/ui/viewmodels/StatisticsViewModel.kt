package com.dev.runningapp.ui.viewmodels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dev.runningapp.repositories.MainRepository


class StatisticsViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {
}