package com.shivamjsr18.blogdisplay

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shivamjsr18.blogdisplay.screen.ScreenRoutes
import com.shivamjsr18.blogdisplay.screen.blog.BlogScreen
import com.shivamjsr18.blogdisplay.screen.home.HomeScreen

@Composable
fun BlogDisplayApp(){
    val navHostController = rememberNavController()

    Scaffold {paddingValues ->
        NavHost(
            navController = navHostController,
            startDestination = ScreenRoutes.HOME_ROUTE,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(ScreenRoutes.HOME_ROUTE){
                val openBlog: (String)->Unit = {link->
                    navHostController.navigate(ScreenRoutes.BLOG_ROUTE+"/${link.replace('/','@')}")
                }
                HomeScreen(openBlog)
            }

            composable(
                route= ScreenRoutes.BLOG_ROUTE+"/{link}",
                arguments = listOf(navArgument("link"){type= NavType.StringType})
            ){navBackStackEntry->
                val link = navBackStackEntry.arguments?.getString("link")?:""
                BlogScreen(blogUrl = link.replace('@','/'))
            }
        }
    }
}