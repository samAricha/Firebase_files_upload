package teka.android.firebase_image_upload.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.security.AccessController.getContext
import java.util.UUID


class StorageUtil{


    companion object {

        suspend fun uploadToStorage(uri: Uri, context: Context, type: String, progressText: String) {
            val storage = Firebase.storage

            // Create a storage reference from our app
            val storageRef = storage.reference

            val unique_image_name = UUID.randomUUID()
            val spaceRef: StorageReference

            var downloadUrl: String

            if (type == "image"){
                spaceRef = storageRef.child("images/$unique_image_name.jpg")
            }else{
                spaceRef = storageRef.child("videos/$unique_image_name.mp4")
            }

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }



            byteArray?.let{
                val uploadTask = spaceRef.putBytes(byteArray)
//                    val taskSnapshot: UploadTask.TaskSnapshot = Tasks.await(uploadTask)
//                    // Retrieve the download URL
//                    val downloadUri: Uri = Tasks.await(taskSnapshot.storage.downloadUrl)
                // Convert Uri to String
//                    downloadUri.toString()

                withContext(Dispatchers.IO) {

                    uploadTask.addOnFailureListener {
                        Toast.makeText(
                            context,
                            "upload failed",
                            Toast.LENGTH_SHORT
                        ).show()
                        // Handle unsuccessful uploads
                    }.addOnSuccessListener { taskSnapshot ->
                        Toast.makeText(
                            context,
                            "upload succeeded: $progressText",
                            Toast.LENGTH_SHORT
                        ).show()
//                        Log.d("Image url", snapshot.toString())
                    }

                }

            }





        }



        fun uplosdImagetoStorageAndGetUrl(uri: Uri, context: Context){

            val type = "image"
            val storage = Firebase.storage

            // Create a storage reference from our app
            val storageRef = storage.reference

            val unique_image_name = UUID.randomUUID()
            val spaceRef: StorageReference

            if (type == "image"){
                spaceRef = storageRef.child("images/$unique_image_name.jpg")
            }else{
                spaceRef = storageRef.child("videos/$unique_image_name.mp4")
            }



            val uploadTask = spaceRef.child("filename")
                .putFile(uri)
            val urlTask: Task<Uri?> = uploadTask.continueWithTask { task ->
                if (!task.isSuccessful()) {
                    throw task.getException()!!
                }

                // Continue with the task to get the download URL
                spaceRef.child("avatarName").downloadUrl
            }.addOnCompleteListener(OnCompleteListener<Uri?> { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result

                    Toast.makeText(
                        context,
                        "upload succeeded: $downloadUri",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Image url", downloadUri.toString())

                } else {
                    // Handle failures
                    // ...
                }
            })
        }

    }
}
