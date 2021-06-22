package dto

data class MarketPrice (
    val time : Time,
    val disclaimer : String,
    val bpi : Bpi
)

data class Bpi (
    val USD : USD
)

data class USD (

    val code : String,
    val rate : String,
    val description : String,
    val rate_float : Double
)

data class Time (

    val updated : String,
    val updatedISO : String,
    val updateduk : String
)



