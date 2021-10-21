package com.team.forecast.ui.weather.adapters

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.team.entities.weather.remote.response.ListItem

class WeahersCallBack constructor(private val oldList: MutableList<ListItem>, private val newList: MutableList<ListItem>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].dtTxt === newList.get(newItemPosition).dtTxt
    }

    override fun areContentsTheSame(oldCourse: Int, newPosition: Int): Boolean {
        val (_, value, name) = oldList[oldCourse]
        val (_, value1, name1) = newList[newPosition]
        return name == name1 && value == value1
    }

}