package com.example.nopshop.screen.checkout

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.room.util.TableInfo
import com.example.nopshop.R
import com.example.nopshop.component.CustomCheckBox
import com.example.nopshop.component.TextFieldCustom
import com.example.nopshop.databinding.FragmentCheckOutBinding
import kotlin.math.round


class CheckOutFragment : Fragment(R.layout.fragment_check_out) {
    private lateinit var binding: FragmentCheckOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                CheckOutScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CheckOutScreen() {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF0BF7EB),
                            Color(0xFF07C5FB),
                            Color(0xFF088DF9),
                        )
                    )
                ), title = {
                Text(
                    text = "One Page Checkout", color = Color.White, fontSize = 18.sp
                )
            }, navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }, actions = {
                BadgedBox(
                    modifier = Modifier
                        .padding(horizontal = 16.dp), badge = {
                        Badge(containerColor = Color.White) {
                            Text(text = "2", modifier = Modifier.semantics { })
                        }
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cart),
                        contentDescription = "Go to cart",
                        tint = Color.White
                    )
                }
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }) { innerPadding ->
            Column {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(top = 16.dp, start = 16.dp, bottom = 16.dp, end = 16.dp),
                    shape = RoundedCornerShape(1.dp),
                    elevation = CardDefaults.cardElevation(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF0BF7EB),
                                        Color(0xFF07C5FB),
                                        Color(0xFF088DF9),
                                    )
                                )
                            )
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            text = "Billing Address",
                            color = Color.White
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                        text = "Address",
                        fontWeight = FontWeight.Bold
                    )
                    TextField(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .height(25.dp),
                        value = "",
                        onValueChange = {},
                        placeholder = { Text(text = "Existing Address") },
                        label = {
                            Text(
                                text = "Existing Address",
                                fontSize = 12.sp,
                                color = Color((0xFF7D828B))
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color(0xFFCDD1D4),
                            focusedIndicatorColor = Color(0xFFCDD1D4)
                        ),
                        trailingIcon = {
                            Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                        })
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomCheckBox()
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = "Ship to the same address",
                            fontSize = 12.sp,
                            color = Color(0xFF384150)
                        )
                    }
                    Text(
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        text = "Select A Billing Address",
                        fontWeight = FontWeight.Bold,
                    )
                    TextField(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                        .height(25.dp),
                        value = "",
                        onValueChange = {},
                        placeholder = { Text(text = "New") },
                        label = {
                            Text(
                                text = "New", fontSize = 14.sp, color = Color((0xFF7D828B))
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            unfocusedIndicatorColor = Color(0xFFCDD1D4),
                            focusedIndicatorColor = Color(0xFFCDD1D4)
                        ),
                        trailingIcon = {
                            Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                        })
                    TextFieldCustom(label = "First Name", value = "") {

                    }
                    TextFieldCustom(label = "Last Name", value = "") {

                    }
                    TextFieldCustom(label = "Email", value = "") {

                    }
                    TextFieldCustom(label = "Company", value = "") {

                    }
                    TextFieldCustom(label = "Country", value = "") {

                    }
                    TextFieldCustom(label = "State/Province", value = "") {

                    }
                    TextFieldCustom(label = "Zip / Postal Code", value = "") {

                    }
                    TextFieldCustom(label = "City", value = "") {

                    }
                    TextFieldCustom(label = "Phone Number", value = "") {

                    }
                    TextFieldCustom(label = "Fax Number", value = "") {

                    }


                }
            }

        }
    }

    @Composable
    @Preview
    fun CheckOutScreenPreview() {
        CheckOutScreen()
    }


}
