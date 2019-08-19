package com.example.firebase.valueobjects

class FirebaseVO {

    var efficiency: Float ?= null
    var power: Float ?= null

    constructor(efficiency: Float?, power: Float?){
        this.efficiency = efficiency
        this.power = power
    }

}