package com.example.firebase.valueobjects

class DateInformationVO {

    var date: String? = null
    var hour: String? = null
    var power: Float? = null

    constructor(date: String?, hour: String?, power: Float?){
        this.date = date
        this.hour = hour
        this.power = power
    }

    constructor()
}