package com.shivamjsr18.blogdisplay.screen.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shivamjsr18.blogdisplay.model.Blog
import com.shivamjsr18.blogdisplay.model.toBlogList
import com.shivamjsr18.blogdisplay.services.apiService.ApiService
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
): ViewModel() {
    private val perPage = 20
    private var currentPage = 1

    var isPageEnded by mutableStateOf(false)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var listOfBlogs by mutableStateOf(emptyList<Blog>())
        private set


    fun onStart(context: Context){
        currentPage=1
        isLoading = true
        currentPage+=1
        viewModelScope.launch {
            try {
                val nextList = apiService.getBlogs(perPage,currentPage).toBlogList()
                if(nextList.blogList.isEmpty()){
                    isPageEnded=true
                }
                else{
                    listOfBlogs = listOfBlogs + nextList.blogList
                }
                isLoading = false
            }
            catch(e: Exception)
            {
                Log.d("Api error",e.message?:"")
                Toast.makeText(context,"Error Occurred: ${e.message}",Toast.LENGTH_LONG).show()
            }
        }
    }

    fun loadNextPg(context: Context){
        isLoading = true
        currentPage+=1
        viewModelScope.launch {
            try {
                val nextList = apiService.getBlogs(perPage,currentPage).toBlogList()
                if(nextList.blogList.isEmpty()){
                    isPageEnded=true
                }
                else{
                    listOfBlogs = listOfBlogs + nextList.blogList
                }
                isLoading = false
            }
            catch(e: Exception)
            {
                Log.d("Api error",e.message?:"")
                Toast.makeText(context,"Error Occurred: ${e.message}",Toast.LENGTH_LONG).show()
            }
        }
    }

}