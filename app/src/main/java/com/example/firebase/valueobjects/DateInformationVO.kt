package com.example.firebase.valueobjects

class DateInformationVO {

    var foreingKey: Int ?= null
    var date: Int? = null
    var month: String? = null
    var power: Float? = null
    var efficiency: Float? = null

    constructor(date: Int?, power: Float?, efficiency: Float?){
        this.date = date
        this.power = power
        this.efficiency = efficiency
    }
    constructor(month: String?, power: Float?, efficiency: Float?){
        this.month = month
        this.power = power
        this.efficiency = efficiency
    }
}