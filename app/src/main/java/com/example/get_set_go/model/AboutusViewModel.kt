package com.example.get_set_go.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.get_set_go.data.AboutusData
import com.example.get_set_go.data.SportsData

class AboutusViewModel:ViewModel() {

    private var _currentSport: MutableLiveData<Sport> = MutableLiveData<Sport>()
    val currentSport: LiveData<Sport>
        get() = _currentSport

    private var _aboutusData: ArrayList<Sport> = ArrayList()
    val sportsData: ArrayList<Sport>
        get() = _aboutusData

    init {
        // Initialize the sports data.
        _aboutusData = AboutusData.getSportsData()
        _currentSport.value = _aboutusData[0]
    }

    fun updateCurrentSport(sport: Sport) {
        _currentSport.value = sport
    }

}