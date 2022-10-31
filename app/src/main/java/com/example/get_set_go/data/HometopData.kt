package com.example.get_set_go.data

import com.example.get_set_go.R
import com.example.get_set_go.model.Sport

object HometopData {
    fun getSportsData(): ArrayList<Sport> {
        return arrayListOf(
            Sport(
                id = 1,
                titleResourceId = R.string.style1,
                subTitleResourceId = R.string.style1_subtitle,
                imageResourceId = R.drawable.o1,
                sportsImageBanner = R.drawable.o1,
                newsDetails = R.string.style1_news,

                ),
            Sport(
                id = 2,
                titleResourceId = R.string.style2,
                subTitleResourceId = R.string.style2_subtitle,
                imageResourceId = R.drawable.o2,
                sportsImageBanner = R.drawable.o2,
                newsDetails = R.string.style2_news,
            ),
            Sport(
                id = 3,
                titleResourceId = R.string.style3,
                subTitleResourceId = R.string.style3_subtitle,
                imageResourceId = R.drawable.o3,
                sportsImageBanner = R.drawable.img_basketball_badri,
                newsDetails = R.string.style3_news,
            ),
            Sport(
                id = 4,
                titleResourceId = R.string.style4,
                subTitleResourceId = R.string.style4_subtitle,
                imageResourceId = R.drawable.o4,
                sportsImageBanner = R.drawable.o3,
                newsDetails = R.string.style4_news,
            ),
            Sport(
                id = 5,
                titleResourceId = R.string.style5,
                subTitleResourceId = R.string.style5_subtitle,
                imageResourceId = R.drawable.o5,
                sportsImageBanner = R.drawable.o4,
                newsDetails = R.string.style5_news,
            ),

        )
    }
}