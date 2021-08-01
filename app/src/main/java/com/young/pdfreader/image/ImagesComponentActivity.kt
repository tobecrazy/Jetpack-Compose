package com.young.pdfreader.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            Images(resourceId = R.drawable.opencv, contentDescription = "Open CV")
        }
    }

    @Composable
    fun Images(resourceId: Int, contentDescription: String) {
        Image(
            painter = painterResource(resourceId),
            contentDescription = contentDescription,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(6.dp)),
            contentScale = ContentScale.Fit
        )
    }
}