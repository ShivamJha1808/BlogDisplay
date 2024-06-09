package com.shivamjsr18.blogdisplay.screen.blog

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BlogScreen(
    blogUrl: String,
    viewModel: BlogViewModel = hiltViewModel()
){

    Log.e("Hello",blogUrl)
    var url = blogUrl
    val context = LocalContext.current
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams =ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    url = if(!viewModel.isInternetAvailable(context)) "file:///android_asset/Error.html"
                    else "file:///android_asset/NoInternet.html"
                }
            }

            settings.javaScriptEnabled =true

            loadUrl(url)
        }
    })
}