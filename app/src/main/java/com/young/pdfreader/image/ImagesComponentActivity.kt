package com.young.pdfreader.image

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material.icons.sharp.Face
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.twotone.Face
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.young.pdfreader.R
import com.young.pdfreader.databinding.ActivityImagesBinding

/**
 * https://developer.android.com/reference/kotlin/androidx/compose/foundation/package-summary
 */
class ImagesComponentActivity : AppCompatActivity() {
    lateinit var databinding: ActivityImagesBinding

    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_images)
        databinding.imagesComposeView.setContent {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(
                    text = "https://coil-kt.github.io/coil/compose/",
                    modifier = Modifier.padding(2.dp)
                )
                //load image form URL
                Spacer(modifier = Modifier.height(10.dp))
                LoadImageFromURL()
                CircleImages(
                    resourceId = R.drawable.look,
                    contentDescription = "Open CV"
                )
                Spacer(modifier = Modifier.height(10.dp))
                Images(resourceId = R.drawable.bmp_image, contentDescription = "BMP image")
                Divider(modifier = Modifier.height(2.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .padding(2.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
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
                    LoadImageVector(iconId = Icons.Filled.Face, Color.Blue)
                    LoadImageVector(iconId = Icons.Rounded.Face, Color.Magenta)
                    LoadImageVector(iconId = Icons.Outlined.Face, Color.Yellow)
                    LoadImageVector(iconId = Icons.TwoTone.Face, Color.Blue)
                    LoadImageVector(iconId = Icons.Sharp.Face, Color.Black)
                }
                Spacer(modifier = Modifier.height(10.dp))
                val image = ImageBitmap.Companion.imageResource(id = R.drawable.bmp_image)
                BitmapImage(image = image, description = "BitMap Image")
                Spacer(modifier = Modifier.height(10.dp))
                //Draw images
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

    @ExperimentalCoilApi
    @Preview(showBackground = true)
    @Composable
    fun LoadImageFromURL() {
        val context = LocalContext.current
        Column() {
            //https://coil-kt.github.io/coil/compose/
            Spacer(modifier = Modifier.height(10.dp))
            val urls = mutableListOf(
                "http://img.logozhan.com/d/file/logo/20190928/b29961bf4df2436c254a544985f4625b.png",
                "http://img.soogif.com/x3UvuPZuhSgZAiRZT9u3B0sxRZhBvEjq.gif",
                "https://www.sap.cn/content/dam/application/shared/logos/sap-logo-china-svg.svg",
                "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png"
            )
            //grey image crop
            var painter = rememberImagePainter(
                data = urls[0],
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    transformations(listOf(GrayscaleTransformation(), CircleCropTransformation()))
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                modifier = Modifier
                    .size(480.dp)
                    .background(Color.Blue),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit
            )

//            val imageLoader = ImageLoader.Builder(context)
//                .componentRegistry {
//                    add(GifDecoder())
//                    add(SvgDecoder(context))
//                }
//                .build()
            //Circle crop
            painter = rememberImagePainter(
                data = urls[1],
                builder = {
                    decoder(
                        if (SDK_INT >= 28) {
                            ImageDecoderDecoder(context)
                        } else {
                            GifDecoder()
                        }
                    )
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
//                    transformations(CircleCropTransformation())
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                modifier = Modifier
                    .size(480.dp)
                    .background(Color.LightGray),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            //Rounded Corners
            painter = rememberImagePainter(
                data = urls[2],
                builder = {
                    decoder(SvgDecoder(context))
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
//                    transformations(RoundedCornersTransformation())
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                modifier = Modifier
                    .size(480.dp)
                    .background(Color.LightGray),
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Inside
            )

            Spacer(modifier = Modifier.height(10.dp))
            Image(
                modifier = Modifier
                    .size(480.dp)
                    .background(Color.Transparent),
                painter = rememberImagePainter(
                    data = urls[3],
                    builder = {
                        crossfade(false)
                        placeholder(R.drawable.placeholder)
                        transformations(BlurTransformation(context, 0.2f, 3f))
                    }
                ),
                contentDescription = null,
            )
        }

    }

    @Composable
    fun Images(resourceId: Int, contentDescription: String) {
        Column() {
            val content = LocalContext.current
            Image(
                painter = painterResource(resourceId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .background(shape = RectangleShape, color = Color.Red)
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }

    @ExperimentalCoilApi
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