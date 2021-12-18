package com.young.pdfreader.migrate

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import com.young.pdfreader.R
import com.young.pdfreader.databinding.ActivityMigrateBinding
import com.young.pdfreader.viewmodel.MainViewModel
import com.young.pdfreader.viewmodel.PDFReaderViewModelFactory

class MigrateActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var dataBinding: ActivityMigrateBinding
    lateinit var composeView: ComposeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_migrate)
        composeView = dataBinding.composeView
        setupViewModel()
        composeView.setContent {
            Column() {
                MigrateTextLayout()
                Spacer(modifier = Modifier.padding(2.dp))
                MessageBoard()
            }
        }

    }

    private fun setupViewModel() {
        viewModel = PDFReaderViewModelFactory().create(MainViewModel::class.java)
        viewModel.message.observe(this, Observer {
            if (null != it) {
                Toast.makeText(this, "ViewModel - message $it", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @Composable
    private fun MigrateTextLayout() {
        val viewModel: MainViewModel = viewModel(factory = PDFReaderViewModelFactory())
        val message by viewModel.message.observeAsState(initial = "")
        Text(
            text = message,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.margin_small))
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }

    val MessageBoard = @Composable {
        Text(
            text = "By Compose Lambada",
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = "Face"
        )

    }

    fun Dashboard(composeParam: @Composable () -> Unit) {

    }
}