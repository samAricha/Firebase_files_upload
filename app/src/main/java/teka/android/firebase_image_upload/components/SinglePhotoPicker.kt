package teka.android.firebase_image_upload.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import teka.android.firebase_image_upload.presentation.ImagePIckerViewModel
import teka.android.firebase_image_upload.utils.StorageUtil


@Composable
fun SinglePhotoPicker(){
    var uri by remember{
        mutableStateOf<Uri?>(null)
    }

    val viewModel: ImagePIckerViewModel = viewModel()


    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    val context = LocalContext.current


    Column{
        Button(onClick = {
            singlePhotoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )

        }){
            Text("Pick Single Image")
        }

        AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(248.dp))

        Button(onClick = {
            uri?.let { viewModel.saveImageToFirebaseStorage(uri = it, context = context, progressText = "1/1") }
//            uri?.let{
//                StorageUtil.uploadToStorage(uri=it, context=context, type="image")
//            }

        }){
            Text("Upload")
        }

    }
}
