
package com.example.get_set_go.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.get_set_go.data.StyleDiamondData
import com.example.get_set_go.data.StyleOvalData
import com.example.get_set_go.data.StyleRoundData

class StyleDiamondViewModel : ViewModel() {    //change the name here

    private var _currentStyleDiamond: MutableLiveData<Style> = MutableLiveData<Style>()
    val currentStyleDiamond: LiveData<Style>
        get() = _currentStyleDiamond

    private var _styleDiamondData: ArrayList<Style> = ArrayList()
    val styleDiamondData: ArrayList<Style>
        get() = _styleDiamondData

    init {
        // Initialize the sports data.
        _styleDiamondData = StyleDiamondData.getStyleDiamondData()
        _currentStyleDiamond.value = _styleDiamondData[0]
    }

    fun updateCurrentStyle(style: Style) {
        _currentStyleDiamond.value = style
    }
}