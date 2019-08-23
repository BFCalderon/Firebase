package com.example.firebase.utils

class ConstantValues() {
    fun getStringMont(month: Int): String{
        return when (month) {
            1 -> "ENERO"
            2 -> "FEBRERO"
            3 -> "MARZO"
            4 -> "ABRIL"
            5 -> "MAYO"
            6 -> "JUNIO"
            7 -> "JULIO"
            8 -> "AGOSTO"
            9 -> "SEPTIEMBRE"
            10 -> "OCTUBRE"
            11 -> "NOVIEMBRE"
            12 -> "DICIEMBRE"
            else -> "MES $month"
        }
    }

    fun getIntMont(month: String): Int{
        return when (month) {
            "ENERO" -> 1
            "FEBRERO" -> 2
            "MARZO" -> 3
            "ABRIL" ->4
            "MAYO" ->5
            "JUNIO" ->6
            "JULIO" -> 7
            "AGOSTO" -> 8
            "SEPTIEMBRE" ->9
            "OCTUBRE" ->10
            "NOVIEMBRE" ->11
            "DICIEMBRE" ->12
            else -> -1
        }
    }
}