package com.example.github.models

data class Usuario(
    var id : Int = 0,
    var node_id : String = "",
    var login : String = "",
    var avatar_url : String = "",
    var url : String = "",
    var type : String = "",
    var site_admin : Boolean,
    var name : String,
    var followers : String,
    var following : String,
    var public_repos : String
)