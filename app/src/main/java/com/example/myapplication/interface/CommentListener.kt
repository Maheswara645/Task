package com.example.myapplication.`interface`

import com.example.myapplication.response.model.DetailsModel

// used to have a bridge between handling values from one screen to another
interface CommentListener {

    fun onsend(detailsModel: DetailsModel,position:Int)
}