package org.d3if3102.fashionstore.ui.screen

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import org.d3if3102.fashionstore.R
import org.d3if3102.fashionstore.model.Produk
import org.d3if3102.fashionstore.model.User
import org.d3if3102.fashionstore.network.ProdukApi
import org.d3if3102.fashionstore.ui.theme.FashionStoreTheme
import org.d3if3102.fashionstore.util.DataStore

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(modifier: Modifier, navController: NavController) {
    val context = LocalContext.current
    val dataStore = DataStore(context)
    val user by dataStore.userFlow.collectAsState(User())
    val viewModel: MainViewModel = viewModel()
    Scaffold(

    ) {
        ListProdukContent(modifier = Modifier.padding(), viewModel)

    }

}

@Composable
fun ListProdukContent(modifier: Modifier, viewModel: MainViewModel) {
    val viewModel: MainViewModel = viewModel()
    val data by viewModel.data
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(data){
            ListProduk(produk = it)
        }
    }
}

@Composable
fun ListProduk(produk: Produk) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .border(1.dp, Color.Gray),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(ProdukApi.getProdukUrl(produk.image))
                .crossfade(true)
                .build(),
            contentDescription = stringResource(id = R.string.gambar, produk.title),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(Color(red = 0f, green = 0f, blue = 0f, alpha = 0.5f))
        ) {
            Text(
                text = produk.title,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = produk.category,
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ListKProdukScreenPreview() {
    FashionStoreTheme {
        MainScreen(modifier = Modifier.fillMaxWidth(), navController = rememberNavController())
    }
}