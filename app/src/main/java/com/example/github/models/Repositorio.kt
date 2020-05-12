package com.example.github.models

data class Repositorio(
    var id : Int = 0,
    var node_id : String = "",
    var name : String = "",
    var full_name : String = "",
    var private : Boolean = false,
    var owner : Usuario,
    var description : String = ""
)