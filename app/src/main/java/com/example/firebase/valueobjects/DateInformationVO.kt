package com.example.firebase.valueobjects

class DateInformationVO {

    var primaryKey: Int ?= null
    var foreingKey: Int ?= null
    var foreingKey1: Int ?= null
    var foreingKey2: Int ?= null
    var date: Int? = null
    var anchor: Int? = null
    var power: Float? = null
    var efficiency: Float? = null

    constructor(power: Float?, efficiency: Float?) {
        this.date = date
        this.power = power
    }

    constructor(primaryKey: Int, date: Int?, power: Float?, efficiency: Float?){
        this.primaryKey = primaryKey
        this.date = date
        this.power = power
        this.efficiency = efficiency
    }
    constructor(power: Float?, efficiency: Float?,foreingKey: Int?){
        this.foreingKey = foreingKey
        this.power = power
        this.efficiency = efficiency
    }
    constructor(foreingKey: Int?){
        this.foreingKey = foreingKey
    }
    constructor(foreingKey: Int?,/* foreingKey1: Int?,*/ date: Int?, power: Float?, efficiency: Float?){
        this.date = date
        this.foreingKey = foreingKey
        this.power = power
        this.efficiency = efficiency
    }
    constructor(foreingKey: Int?, foreingKey1: Int?, date: Int?, power: Float?, efficiency: Float?){
        this.date = date
        this.foreingKey = foreingKey
        this.foreingKey1 = foreingKey1
        this.power = power
        this.efficiency = efficiency
    }
    constructor(foreingKey: Int?, foreingKey1: Int?, foreingKey2: Int?, date: Int?, power: Float?, efficiency: Float?){
        this.date = date
        this.foreingKey = foreingKey
        this.foreingKey1 = foreingKey1
        this.foreingKey2 = foreingKey2
        this.power = power
        this.efficiency = efficiency
    }
    constructor()
}