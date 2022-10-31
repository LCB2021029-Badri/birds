package com.example.get_set_go.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.get_set_go.data.StyleOvalData
import com.example.get_set_go.data.StyleRoundData
import com.example.get_set_go.data.StyleSquareData

class StyleSquareViewModel : ViewModel() {    //change the name here

    private var _currentStyleSquare: MutableLiveData<Style> = MutableLiveData<Style>()
    val currentStyleSquare: LiveData<Style>
        get() = _currentStyleSquare

    private var _styleSquareData: ArrayList<Style> = ArrayList()
    val styleSquareData: ArrayList<Style>
        get() = _styleSquareData

    init {
        // Initialize the sports data.
        _styleSquareData = StyleSquareData.getStyleSquareData()
        _currentStyleSquare.value = _styleSquareData[0]
    }

    fun updateCurrentStyle(style: Style) {
        _currentStyleSquare.value = style
    }
}