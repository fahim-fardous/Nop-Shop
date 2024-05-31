package com.example.nopshop.utils

object Value {
    var itemValue = 0
    fun setValue(newValue: Int){
        itemValue = newValue
    }
    fun getValue():Int{
        println(itemValue)
        return itemValue
    }
    fun incrementValue():Int{
        return itemValue++
    }
    fun decrementValue():Int{
        return itemValue--
    }
}