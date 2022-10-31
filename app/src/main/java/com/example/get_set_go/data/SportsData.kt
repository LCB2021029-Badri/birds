package com.example.get_set_go.data

import com.example.get_set_go.R
import com.example.get_set_go.model.Sport

object SportsData{
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
                sportsImageBanner = R.drawable.o3,
                newsDetails = R.string.style3_news,
            ),
            Sport(
                id = 4,
                titleResourceId = R.string.style4,
                subTitleResourceId = R.string.style4_subtitle,
                imageResourceId = R.drawable.o4,
                sportsImageBanner = R.drawable.o4,
                newsDetails = R.string.style4_news,
            ),
            Sport(
                id = 5,
                titleResourceId = R.string.style5,
                subTitleResourceId = R.string.style5_subtitle,
                imageResourceId = R.drawable.o5,
                sportsImageBanner = R.drawable.o5,
                newsDetails = R.string.style5_news,
            ),
            Sport(
                id = 6,
                titleResourceId = R.string.style6,
                subTitleResourceId = R.string.style6_subtitle,
                imageResourceId = R.drawable.o6,
                sportsImageBanner = R.drawable.o6,
                newsDetails = R.string.style6_news,
            ),
            Sport(
                id = 7,
                titleResourceId = R.string.style7,
                subTitleResourceId = R.string.style7_subtitle,
                imageResourceId = R.drawable.o7,
                sportsImageBanner = R.drawable.o7,
                newsDetails = R.string.style7_news,
            ),
            Sport(
                id = 8,
                titleResourceId = R.string.style8,
                subTitleResourceId = R.string.style8_subtitle,
                imageResourceId = R.drawable.o8,
                sportsImageBanner = R.drawable.o8,
                newsDetails = R.string.style7_news,
            ),
        )
    }
}