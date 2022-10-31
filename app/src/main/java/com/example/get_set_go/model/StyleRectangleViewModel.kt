package com.example.get_set_go.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.get_set_go.data.StyleOvalData
import com.example.get_set_go.data.StyleRectangleData
import com.example.get_set_go.data.StyleRoundData

class StyleRectangleViewModel : ViewModel() {    //change the name here

    private var _currentStyleRectangle: MutableLiveData<Style> = MutableLiveData<Style>()
    val currentStyleRectangle: LiveData<Style>
        get() = _currentStyleRectangle

    private var _styleRectangleData: ArrayList<Style> = ArrayList()
    val styleRectangleData: ArrayList<Style>
        get() = _styleRectangleData

    init {
        // Initialize the sports data.
        _styleRectangleData = StyleRectangleData.getStyleRectangleData()
        _currentStyleRectangle.value = _styleRectangleData[0]
    }

    fun updateCurrentStyle(style: Style) {
        _currentStyleRectangle.value = style
    }
}