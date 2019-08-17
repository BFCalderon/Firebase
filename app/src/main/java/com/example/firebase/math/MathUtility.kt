package com.example.firebase.math

import kotlin.math.pow
import kotlin.math.roundToInt

class MathUtility {

    fun roundDouble(value: Double, places: Int): Float {
        val scale = 10.0.pow(places.toDouble())
        return ((value * scale).roundToInt() / scale).toFloat()
    }
}