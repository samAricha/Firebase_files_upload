package teka.android.firebase_image_upload.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun MultiplePhotoPicker(){
    val context = LocalContext.current
    val viewModel: ImagePIckerViewModel = viewModel()


    var imageUris by remember{
        mutableStateOf<List<Uri>>(emptyList())
    }

    val multiplePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = {
            imageUris = it
        }
    )

    Column{

        LazyColumn{
            item{
                Button(onClick = {
                    multiplePhotoPicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }) {
                    Text("Pick Multiple Images")

                }
            }

            items(imageUris){ uri ->
                AsyncImage(model = uri, contentDescription = null, modifier = Modifier.size(248.dp))

            }

            item {
                Button(onClick = {
                    val imageNumber = imageUris.size
                    imageUris.forEachIndexed { index,  uri ->
                        val uploadNumber = index + 1
                        val totalImages = imageUris.size
                        val progressText = "$uploadNumber/$totalImages"


                        uri?.let { viewModel.saveImageToFirebaseStorage(uri = it, context = context, progressText = progressText) }
                        uri?.let{
//                    StorageUtil.uploadToStorage(uri=it, context=context, type="image")
                        }
                    }

                }){
                    Text("Upload")
                }
            }

        }


    }


}

