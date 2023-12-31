package teka.android.firebase_image_upload.presentation

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage

class ImagePIckerViewModel(private val applicationContext: Context): ViewModel() {

    var selectedImageUri by mutableStateOf<Uri?>(null)

    fun pushImageToFirebase(){

//        val documentId = it.id
        val documentId = "it.id"


        selectedImageUri?.let { uri ->
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child("images/$documentId.jpg")
            val uploadTask = imageRef.putFile(uri)

            uploadTask.addOnSuccessListener {
                // Image upload successful
                Toast.makeText(applicationContext, "Image uploaded successfully!", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener { e ->
                // Image upload failed
                Toast.makeText(applicationContext, "Image upload failed: $e", Toast.LENGTH_SHORT).show()
            }
        }

    }


}