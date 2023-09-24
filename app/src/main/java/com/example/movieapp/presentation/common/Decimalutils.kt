package com.example.movieapp.presentation.common

import java.text.DecimalFormat

object Decimalutils {

    fun Double.roundOneDecimal(): Double {
        val decimalFormat = DecimalFormat("#.#")
        return decimalFormat.format(this).toDouble()
    }
}