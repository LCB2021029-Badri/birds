package com.example.get_set_go.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.get_set_go.data.StyleOvalData
import com.example.get_set_go.data.StyleRoundData

class StyleRoundViewModel : ViewModel() {    //change the name here

    private var _currentStyleRound: MutableLiveData<Style> = MutableLiveData<Style>()
    val currentStyleRound: LiveData<Style>
        get() = _currentStyleRound

    private var _styleRoundData: ArrayList<Style> = ArrayList()
    val styleRoundData: ArrayList<Style>
        get() = _styleRoundData

    init {
        // Initialize the sports data.
        _styleRoundData = StyleRoundData.getStyleRoundData()
        _currentStyleRound.value = _styleRoundData[0]
    }

    fun updateCurrentStyle(style: Style) {
        _currentStyleRound.value = style
    }
}