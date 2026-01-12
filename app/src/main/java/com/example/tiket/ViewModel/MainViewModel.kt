package com.example.tiket.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tiket.Domain.FlightModel
import com.example.tiket.Domain.LocationModel
import com.example.tiket.Repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    fun loadLocation(): LiveData<MutableList<LocationModel>> {
        return repository.loadLocation()
    }

    fun loadFiltered(from:String, to:String): LiveData<MutableList<FlightModel>> {
        return repository.loadFiltered(from, to)
    }
}