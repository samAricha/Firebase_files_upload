package teka.android.firebase_image_upload.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
//                        val storageRef = Firebase.storage.reference
//                        storageRef.child("folder/subfolder/picture.png").downloadUrl
//                            .addOnSuccessListener { url ->
//                                // do whatever with your url
//                            }
//                            .addOnFailureListener { exception ->
//                                Log.e(TAG, "Exception: ${exception.message}")
//                            }

                        Toast.makeText(
                            context,
                            "upload succeeded: $progressText",
                            Toast.LENGTH_SHORT
                        ).show()
//                        Log.d("Image url", snapshot.toString())
                    }.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val fileUri = task.result.uploadSessionUri.toString()

                            val downloadUri = task.result.uploadSessionUri

                            //URL
//                            val url = downloadUri!!.result


//                            Toast.makeText(
//                                context,
//                                "upload Complete: $downloadUri",
//                                Toast.LENGTH_SHORT
//                            ).show()
                            Log.d("Image url", downloadUri.toString())
                        }
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



            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }



            byteArray?.let{
                val uploadTask = spaceRef.putBytes(byteArray)

                val urlTask = uploadTask.continueWithTask<Uri> { task ->
                    if (!task.isSuccessful) {
                        throw task.exception!!
                    }

                    // Continue with the task to get the download URL
                    spaceRef.child("avatarName").downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
//                            spaceRef.child("avatar_image")
//                            .setValue(downloadUri.toString())

                        Toast.makeText(
                            context,
                            "success",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // Handle failures
                        // ...
                        Toast.makeText(
                            context,
                            "Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


            }




        }

    }
}
