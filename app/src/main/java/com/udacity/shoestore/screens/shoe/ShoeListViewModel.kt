package com.udacity.shoestore.screens.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private var _listShoe = MutableLiveData<ArrayList<Shoe>>()
    val listShoe: LiveData<ArrayList<Shoe>>
        get() = _listShoe

    init {
        _listShoe.value = arrayListOf()
    }

    fun saveShoe(shoe: Shoe) {
        val list = _listShoe.value
        list?.add(shoe)
        _listShoe.value = list
    }
}