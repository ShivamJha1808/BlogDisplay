package com.shivamjsr18.blogdisplay.model.apiModel

import com.google.gson.annotations.SerializedName

data class Links(
    val about: List<About>,
    val author: List<Author>,
    val collection: List<Collection>,
    val curies: List<Cury>,
    @SerializedName("predecessor-version")
    val predecessor_version: List<PredecessorVersion>,
    val replies: List<Reply>,
    val self: List<Self>,
    @SerializedName("version-history")
    val version_history: List<VersionHistory>,
    @SerializedName("wp:attachment")
    val wpAttachment: List<WpAttachment>,
    @SerializedName("wp:featuredmedia")
    val wpFeaturedmedia: List<WpFeaturedmedia>,
    @SerializedName("wp:term")
    val wpTerm: List<WpTerm>
)