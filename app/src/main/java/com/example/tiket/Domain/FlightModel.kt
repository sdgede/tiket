package com.example.tiket.Domain

import java.io.Serializable


data class FlightModel(
    var ArilineLogo: String = "",
    var ArilineName: String = "",
    var ArrivaTime: String = "",
    var ClassSeat: String = "",
    var Date: String = "",
    var From: String = "",
    var FromShort: String = "",
    var NumberSeat: Int = 0,
    var Price: Double = 0.0,
    var Passeger: String = "",
    var Seates: String = "",
    var ReservedSeate: String = "",
    var Time: String = "",
    var To: String = "",
    var ToShort: String = ""
): Serializable