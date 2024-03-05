package com.example.move.sign.sign_up

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.move.R
import com.example.move.homescreen.HomeScreenActivity
import com.example.move.sign.sign_in.SignInActivity
import com.example.move.sign.sign_in.User
import com.example.move.utils.Preferences
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.util.UUID


@Suppress("DEPRECATION")
class SignInPhotoActivity : AppCompatActivity(), PermissionListener {

    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd: Boolean = false
    lateinit var filePath: Uri

    lateinit var storage : FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var preference : Preferences
    lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photo)

        preference = Preferences(this)
        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference

        val btn_skip = findViewById<Button>(R.id.btn_skip)
        val btn_save = findViewById<Button>(R.id.btn_save)
        val iv_add = findViewById<ImageView>(R.id.iv_add)
        val iv_profile = findViewById<ImageView>(R.id.iv_profile)
        val tv_hello = findViewById<TextView>(R.id.tv_hello)

        tv_hello.text = "Welcome\n"+user.Name
        iv_add.setOnClickListener {
            if (statusAdd){
                statusAdd = false
                btn_save.visibility = View.VISIBLE
                iv_add.setImageResource(R.drawable.btn_upload)
                iv_profile.setImageResource(R.drawable.user_pic)
            }else{
                Dexter.withActivity(this)
                 .withPermission(android.Manifest.permission.CAMERA)
                 .withListener(this)
                 .check()

                ImagePicker.with(this)
                    .cameraOnly()
                    .start()
            }
        }

        val btn_back = findViewById<ImageView>(R.id.imageView5)

        btn_back.setOnClickListener {
            val intent = Intent(this@SignInPhotoActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
        btn_skip.setOnClickListener {
            finishAffinity()
            val go_home = Intent(this@SignInPhotoActivity,
                HomeScreenActivity::class.java)
            startActivity(go_home)
        }
        btn_save.setOnClickListener {
            if(filePath != null){
                var ProgressDialog = ProgressDialog(this)
                ProgressDialog.setTitle("Uploading...")
                ProgressDialog.show()

                var ref = storageReference.child("images/"+ UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener{
                        ProgressDialog.dismiss()
                        Toast.makeText(this@SignInPhotoActivity, "Uploaded", Toast.LENGTH_LONG).show()

                        ref.downloadUrl.addOnSuccessListener {
                            preference.setValues("url", it.toString())
                        }
                        finishAffinity()
                        val go_home = Intent(this@SignInPhotoActivity,
                            HomeScreenActivity::class.java)
                        startActivity(go_home)
                    }
                    .addOnFailureListener {
                        ProgressDialog.dismiss()
                        Toast.makeText(this@SignInPhotoActivity, "Failed", Toast.LENGTH_LONG).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        ProgressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                    }
            }else{


            }
        }

    }


    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
            takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                this.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        Toast.makeText(this@SignInPhotoActivity, "Failed", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Tergesah? Klik tombol upload nanti aja", Toast.LENGTH_LONG).show()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val iv_add = findViewById<ImageView>(R.id.iv_add)
        val iv_profile = findViewById<ImageView>(R.id.iv_profile)
        val btn_save = findViewById<Button>(R.id.btn_save)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
           var bitmap = data?.extras?.get("data") as Bitmap
            statusAdd = true

            filePath = data.data!!
            Glide.with(this)
                .load(bitmap)
                .apply(RequestOptions.circleCropTransform())
                .into(iv_profile)
            btn_save.visibility = View.VISIBLE
            iv_add.setImageResource(R.drawable.ic_btn_delete)
        }
    }
}