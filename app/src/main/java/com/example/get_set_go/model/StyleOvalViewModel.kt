package com.example.get_set_go.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.get_set_go.data.StyleOvalData

class StyleOvalViewModel : ViewModel() {    //change the name here

    private var _currentStyleOval: MutableLiveData<Style> = MutableLiveData<Style>()
    val currentStyleOval: LiveData<Style>
        get() = _currentStyleOval

    private var _styleOvalData: ArrayList<Style> = ArrayList()
    val styleOvalData: ArrayList<Style>
        get() = _styleOvalData

    init {
        // Initialize the sports data.
        _styleOvalData = StyleOvalData.getStyleOvalData()
        _currentStyleOval.value = _styleOvalData[0]
    }

    fun updateCurrentStyle(style: Style) {
        _currentStyleOval.value = style
    }
}