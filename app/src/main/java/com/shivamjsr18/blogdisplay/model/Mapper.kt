package com.shivamjsr18.blogdisplay.model

import com.shivamjsr18.blogdisplay.model.apiModel.ApiResponse
import com.shivamjsr18.blogdisplay.model.apiModel.ApiResponseItem



fun ApiResponseItem.toBlog(): Blog{
    return Blog(
        id=id,
        link=link,
        jetpack_featured_media_url = jetpack_featured_media_url,
        title = Title(rendered = title.rendered)
    )
}

fun ApiResponse.toBlogList(): BlogList{
    var blogList = mutableListOf<Blog>()
    this.forEach{apiResponseItem->
        blogList.add(
            apiResponseItem.toBlog()
        )
    }
    return BlogList(blogList)
}