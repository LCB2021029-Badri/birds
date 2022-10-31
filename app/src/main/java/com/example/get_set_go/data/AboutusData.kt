package com.example.get_set_go.data

import com.example.get_set_go.R
import com.example.get_set_go.model.Sport

object AboutusData {
    fun getSportsData(): ArrayList<Sport>{
        return arrayListOf(
            Sport(
                id = 1,
                titleResourceId = R.string.aboutus_badri,
                subTitleResourceId = R.string.aboutus_work_badri,
                imageResourceId = R.drawable.badri2,
                sportsImageBanner = R.drawable.badri,
                newsDetails = R.string.aboutus_badri_news,

                ),
            Sport(
                id = 2,
                titleResourceId = R.string.aboutus_bindu,
                subTitleResourceId = R.string.aboutus_work_bindu,
                imageResourceId = R.drawable.bindu,
                sportsImageBanner = R.drawable.bindu2,
                newsDetails = R.string.aboutus_bindu_news,

                ),
            Sport(
                id = 3,
                titleResourceId = R.string.aboutus_greeshma,
                subTitleResourceId = R.string.aboutus_work_greeshma,
                imageResourceId = R.drawable.greeshma2,
                sportsImageBanner = R.drawable.greeshma,
                newsDetails = R.string.aboutus_greeshma_news,

                ),
        )
    }
}