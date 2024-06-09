package com.shivamjsr18.blogdisplay.model

data class Blog(
    val id:Int,
    val title:Title,
    val link: String,
    val jetpack_featured_media_url: String?
)


data class Title(
    val rendered: String
)