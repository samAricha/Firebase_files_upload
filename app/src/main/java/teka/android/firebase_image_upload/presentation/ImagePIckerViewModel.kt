package teka.android.firebase_image_upload.presentation

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import teka.android.firebase_image_upload.utils.StorageUtil

class ImagePIckerViewModel(): ViewModel() {

    var selectedImageUri by mutableStateOf<Uri?>(null)


    fun saveImageToFirebaseStorage(uri:Uri, context: Context, progressText: String){
        viewModelScope.launch {
            uri.let{
                StorageUtil.uploadToStorage(uri=it, context=context, type="image", progressText = progressText)
//                StorageUtil.uplosdImagetoStorageAndGetUrl(uri=it, context=context)
            }
        }

    }


    fun pushImageToFirebase(){

//        val documentId = it.id
        val documentId = "it.id"


//        selectedImageUri?.let { uri ->
//            val storageRef = FirebaseStorage.getInstance().reference
//            val imageRef = storageRef.child("images/$documentId.jpg")
//            val uploadTask = imageRef.putFile(uri)
//
//            uploadTask.addOnSuccessListener {
//                // Image upload successful
//                Toast.makeText(applicationContext, "Image uploaded successfully!", Toast.LENGTH_SHORT).show()
//            }.addOnFailureListener { e ->
//                // Image upload failed
//                Toast.makeText(applicationContext, "Image upload failed: $e", Toast.LENGTH_SHORT).show()
//            }
//        }

    }


}