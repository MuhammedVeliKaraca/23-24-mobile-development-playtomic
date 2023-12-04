package edu.ap.mobiledevelopmentproject

data class Match(
    val id: String,
    val location: String,
    val day: String,
    val time: String,
    val players: List<String>
)