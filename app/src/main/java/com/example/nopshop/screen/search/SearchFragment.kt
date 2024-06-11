package com.example.nopshop.screen.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.nopshop.R

class SearchFragment : Fragment(R.layout.fragment_search) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                SearchScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchScreen() {
        var searchQuery by remember { mutableStateOf("") }
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF0BF7EB),
                            Color(0xFF07C5FB),
                            Color(0xFF088DF9),
                        )
                    )
                ),
                title = {
                    Text(
                        text = "Search",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 18.dp)
                    )
                },
                actions = {

                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Box(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
                    Box(modifier = Modifier
                        .semantics {
                            isTraversalGroup = true
                        }
                        .zIndex(1f)
                        .fillMaxWidth()) {
                        DockedSearchBar(
                            modifier = Modifier.align(Alignment.TopCenter),
                            query = searchQuery,
                            onQueryChange = { searchQuery = it },
                            onSearch = {},
                            active = false,
                            onActiveChange = {},
                            placeholder = { Text(text = "Search coming soon...") },
                            leadingIcon = {
                                Icon(Icons.Default.Search, contentDescription = null)
                            }
                        ) {}
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    private fun SearchScreenPreview() {
        SearchScreen()
    }


}