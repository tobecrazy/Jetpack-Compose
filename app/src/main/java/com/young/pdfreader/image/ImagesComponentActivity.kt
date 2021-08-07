package com.young.pdfreader.image

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import com.young.pdfreader.R
import com.young.pdfreader.databinding.ActivityImagesBinding

/**
 * https://developer.android.com/reference/kotlin/androidx/compose/foundation/package-summary
 */
class ImagesComponentActivity : AppCompatActivity() {
    lateinit var databinding: ActivityImagesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_images)
        databinding.imagesComposeView.setContent {
            val context = LocalContext.current
            Column {
                CircleImages(
                    resourceId = R.drawable.look,
                    contentDescription = "Open CV"
                )
                Images(resourceId = R.drawable.bmp_image, contentDescription = "BMP image")
                Divider(modifier = Modifier.height(2.dp))
                Row() {
                    LoadImageVector(iconId = Icons.Rounded.Menu, Color.Green)
                    LoadImageVector(iconId = Icons.Rounded.Favorite, Color.Red)
                    LoadImageVector(iconId = Icons.Rounded.Close, Color.Blue)
                    LoadImageVector(iconId = Icons.Outlined.Search, Color.Cyan)
                    LoadImageVector(iconId = Icons.Outlined.Home, Color.Red)
                    LoadImageVector(iconId = Icons.Filled.Home, Color.Red)
                    LoadImageVector(iconId = Icons.Rounded.Home, Color.Red)
                    LoadImageVector(iconId = Icons.Sharp.Home, Color.Red)
                    LoadImageVector(iconId = Icons.TwoTone.Home, Color.Red)
                    LoadImageVector(iconId = Icons.Outlined.Add, Color.Black)
                    LoadImageVector(iconId = Icons.Filled.Add, Color.Unspecified)
                    LoadImageVector(iconId = Icons.Outlined.Edit, Color.Black)
                    LoadImageVector(iconId = Icons.Rounded.Edit, Color.White)
                    LoadImageVector(iconId = Icons.TwoTone.Star, Color.Magenta)
                    LoadImageVector(iconId = Icons.Filled.Star, Color.Magenta)
                }
                val image = ImageBitmap.Companion.imageResource(id = R.drawable.bmp_image)
                BitmapImage(image = image, description = "BitMap Image")
                val painter = remember {
                    object : Painter() {
                        override val intrinsicSize: Size
                            get() = Size(200.0f, 200.0f)

                        override fun DrawScope.onDraw() {
                            drawRect(color = Color.Red)
                            drawCircle(color = Color.Green)
                            drawLine(
                                Color.Blue,
                                start = Offset(100f, 0f),
                                end = Offset(100f, 200f),
                                strokeWidth = 10f
                            )

                            drawLine(
                                Color.Cyan,
                                start = Offset(0f, 100f),
                                end = Offset(200f, 100f),
                                strokeWidth = 10f
                            )
                        }
                    }
                }
                ImageDrawer(painter = painter, description = "Painter image")
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

    /**
     * Icon from https://fonts.google.com/icons?selected=Material+Icons+Outlined
     */
    @Composable
    fun LoadImageVector(iconId: ImageVector, tint: Color) {
        Icon(
            iconId,
            contentDescription = "Localized description",
            tint = tint,
            modifier = Modifier.background(
                Color.LightGray
            )
        )
    }

    @Composable
    fun BitmapImage(image: ImageBitmap, description: String) {
        Image(
            painter = BitmapPainter(image = image, IntOffset(60, 62), IntSize(500, 400)),
            contentDescription = description,
            alignment = Alignment.Center,
            alpha = 0.7f,
            contentScale = ContentScale.Fit
        )
    }

    @Composable
    fun ImageDrawer(painter: Painter, description: String) {
        Image(
            painter = painter,
            contentDescription = description,
            modifier = Modifier.size(200.dp, 200.dp)
        )
    }
}