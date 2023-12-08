package teka.android.firebase_image_upload.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import teka.android.firebase_image_upload.ImageItem

class FirebaseImageRetrievalUtil {

    companion object {

        suspend fun retrieveImageUris(context: Context){
            val storage = Firebase.storage
            // Create a storage reference from our app
            val storageRef = storage.reference
            val imageFolderRef = storageRef.child("images")

            val imageList : ArrayList<ImageItem> = ArrayList()

            val listAllTask: Task<ListResult> = imageFolderRef.listAll()
            listAllTask.addOnCompleteListener { result ->
                val items: List<StorageReference> = result.result!!.items
                items.forEachIndexed { index, storageReference ->
                    storageReference.downloadUrl.addOnSuccessListener {
                        Log.d("storageRef", "$it")
                        imageList.add(ImageItem(it.toString()))
                    }.addOnCompleteListener{

                        Toast.makeText(
                            context,
                            "upload completed: ${imageList[0]}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

            }



        }

    }
}