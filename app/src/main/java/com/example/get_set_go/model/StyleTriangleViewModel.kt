package com.example.get_set_go.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.get_set_go.data.StyleOvalData
import com.example.get_set_go.data.StyleRoundData
import com.example.get_set_go.data.StyleTriangleData

class StyleTriangleViewModel : ViewModel() {    //change the name here

    private var _currentStyleTriangle: MutableLiveData<Style> = MutableLiveData<Style>()
    val currentStyleTriangle: LiveData<Style>
        get() = _currentStyleTriangle

    private var _styleTriangleData: ArrayList<Style> = ArrayList()
    val styleTriangleData: ArrayList<Style>
        get() = _styleTriangleData

    init {
        // Initialize the sports data.
        _styleTriangleData = StyleTriangleData.getStyleTriangleData()
        _currentStyleTriangle.value = _styleTriangleData[0]
    }

    fun updateCurrentStyle(style: Style) {
        _currentStyleTriangle.value = style
    }
}