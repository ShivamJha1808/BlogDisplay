package com.shivamjsr18.blogdisplay.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.shivamjsr18.blogdisplay.model.Blog
import com.shivamjsr18.blogdisplay.model.Title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    openBlog: (String)->Unit,
    viewModel: HomeViewModel = hiltViewModel()
){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home")})
        }
    ) {paddingValues->

        if(viewModel.listOfBlogs.isEmpty() && !viewModel.isLoading){
            viewModel.onStart(LocalContext.current)
        }
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ){
            items(viewModel.listOfBlogs.size){i->
                if(i>=viewModel.listOfBlogs.size-1 && !viewModel.isLoading){
                    viewModel.loadNextPg(LocalContext.current)
                }
                BlogCard(blog = viewModel.listOfBlogs[i],openBlog)
            }
            if(!viewModel.isPageEnded)
            {
                item {
                    Row(
                        modifier = Modifier
                            .then(
                                if (viewModel.listOfBlogs.isEmpty())
                                    Modifier.fillMaxSize()
                                else
                                    Modifier.fillMaxWidth()
                            )
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogCard(blog: Blog, openBlog: (String)->Unit){
    Card(
        modifier = Modifier.padding(top=16.dp,start=16.dp,end=16.dp),
        onClick = {openBlog(blog.link)}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(8.dp)
        ) {
            AsyncImage(
                model = blog.jetpack_featured_media_url,
                contentDescription = "Image",
                modifier = Modifier
                    .weight(4f)
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = blog.title.rendered,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(6f)
            )

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomePreview(){
    BlogCard(
        blog = Blog(
            id = 1,
            link = "",
            title = Title("What should you do after falling for a financial scam/fraud? How to recover your money?"),
            jetpack_featured_media_url = "https://i0.wp.com/blog.vrid.in/wp-content/uploads/2024/06/161.png?fit=1920%2C1080&ssl=1"
        ),
        {}
    )
}