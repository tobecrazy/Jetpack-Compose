package com.young.pdfreader.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import com.young.pdfreader.R
import com.young.pdfreader.databinding.ActivityImagesBinding

class ImagesComponentActivity : AppCompatActivity() {
    lateinit var databinding: ActivityImagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_images)
        databinding.imagesComposeView.setContent {
            Column() {
                CircleImages(
                    resourceId = R.drawable.look,
                    contentDescription = "Open CV"
                )
                Images(resourceId = R.drawable.bmp_image, contentDescription = "BMP image")
                LoadAnimationVector(id = 1)
            }

        }
    }

    @Composable
    fun Images(resourceId: Int, contentDescription: String) {
        Image(
            painter = painterResource(resourceId),
            contentDescription = contentDescription,
            modifier = Modifier
                .background(shape = RectangleShape, color = Color.Red)
                .wrapContentWidth()
                .wrapContentHeight()
                .clip(shape = CircleShape),
            contentScale = ContentScale.Fit
        )
    }

    @Composable
    fun CircleImages(resourceId: Int, contentDescription: String) {
        Card(
            backgroundColor = Color.Cyan,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = 6.dp
        ) {
            Images(resourceId = resourceId, contentDescription = contentDescription)

        }

    }

    @Composable
    fun LoadAnimationVector(id:Int){
        Icon(Icons.Rounded.Menu, contentDescription = "Localized description")
    }
}