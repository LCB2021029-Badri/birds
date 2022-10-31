package com.example.get_set_go

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.get_set_go.databinding.ActivityCameraBinding
import com.example.get_set_go.ml.LiteModelAiyVisionClassifierBirdsV13
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.ByteBuffer
import java.util.*

class CameraActivity : AppCompatActivity() {

    lateinit var binding : ActivityCameraBinding
    lateinit var imageViewC: ImageView
    private lateinit var button: Button
    private lateinit var tvOutput: TextView
    private val GALLERY_REQUEST_CODE = 123

    lateinit var imageView: ImageView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var user : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageViewC = binding.imageViewC
        button = binding.btnCameraC
        tvOutput = binding.tvOutput
        val buttonLoad = binding.btnGalleryC

        button.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED
            ) {
                takePicturePreview.launch(null)
            }
            else {
                requestPermission.launch(android.Manifest.permission.CAMERA)
            }
        }
        buttonLoad.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                val mimeTypes = arrayOf("image/jpeg","image/png","image/jpg")
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                onresult.launch(intent)
            }else {
                requestPermission.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }

        //to redirct user to google search for the scientific name
        tvOutput.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=${tvOutput.text}"))
            startActivity(intent)
        }
        // to download image when longPress on ImageView
        imageViewC.setOnLongClickListener {
            requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            return@setOnLongClickListener true
        }



        // converting to bitmap
        imageView = findViewById(R.id.share_app)
        val bitmapDrawable = imageView.getDrawable() as BitmapDrawable
        val bitmap = bitmapDrawable.bitmap

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            it.isChecked = true   // changes color when we click on "nav_menu item"
            when(it.itemId){
                R.id.nav_drawer_home -> {
                    Toast.makeText(applicationContext,"Clicked Home", Toast.LENGTH_SHORT).show()
//                    replaceFragment(HomepageFragment(),it.title.toString())
                    startActivity(Intent(this,SecondActivity::class.java))
                }
                R.id.nav_drawer_cam -> {
                    Toast.makeText(applicationContext,"Clicked Get Started !", Toast.LENGTH_SHORT).show()
//                    replaceFragment(CamFragment(),it.title.toString())
                    startActivity(Intent(this,CameraActivity::class.java))
                }
                R.id.nav_drawer_top -> {
                    Toast.makeText(applicationContext,"Top Searches", Toast.LENGTH_SHORT).show()
//                    replaceFragment(RecommendationsFragment(),it.title.toString())
                    startActivity(Intent(this,ThirdActivity::class.java))
                }
                R.id.nav_drawer_about->{
                    Toast.makeText(applicationContext,"Clicked View", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,FourthActivity::class.java))
                }
                R.id.nav_drawer_logout -> {
                    Toast.makeText(applicationContext,"Logged out", Toast.LENGTH_SHORT).show()
                    user = FirebaseAuth.getInstance()
                    user.signOut()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                R.id.nav_drawer_share ->{
                    Toast.makeText(applicationContext,"Clicked Share", Toast.LENGTH_SHORT).show()
                    shareImage(bitmap)  // calling created share function
                }
                R.id.nav_drawer_rate -> {
                    Toast.makeText(applicationContext,"Not a Google Developer, So can't give us Rating !!", Toast.LENGTH_SHORT).show()
//                    startReviewFlow() // for rating if we are a developer
                }
                R.id.nav_drawer_contact -> {
                    Toast.makeText(applicationContext,"Contact Us", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,ContactusActivity::class.java))
                }
            }
            true
        }

    }

    //request camera permission
    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){ granted->
        if (granted){
            takePicturePreview.launch(null)
        }else {
            Toast.makeText(this, "Permission Denied !! Try again", Toast.LENGTH_SHORT).show()
        }
    }

    //launch camera and take picture
    private val takePicturePreview = registerForActivityResult(ActivityResultContracts.TakePicturePreview()){ bitmap->
        if(bitmap != null){
            imageViewC.setImageBitmap(bitmap)
            outputGenerator(bitmap)
        }
    }

    //to get image from gallery
    private val onresult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        Log.i("TAG", "This is the result: ${result.data} ${result.resultCode}")
        onResultReceived(GALLERY_REQUEST_CODE,result)
    }

    private fun  onResultReceived(requestCode: Int, result: ActivityResult?){
        when(requestCode){
            GALLERY_REQUEST_CODE ->{
                if (result?.resultCode == Activity.RESULT_OK){
                    result.data?.data?.let{uri ->
                        Log.i("TAG", "onResultReceived: $uri")
                        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
                        imageViewC.setImageBitmap(bitmap)
                        outputGenerator(bitmap)
                    }
                }else {
                    Log.e("TAG", "onActivityResult: error in selecting image")
                }
            }
        }
    }

    private fun outputGenerator(bitmap: Bitmap){
        val BirdsModel = LiteModelAiyVisionClassifierBirdsV13.newInstance(this)

// Creates inputs for reference.

        val newBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true)
        val tfiimage = TensorImage.fromBitmap(newBitmap)



// Runs model inference and gets result.
        val outputs = BirdsModel.process(tfiimage)
            .probabilityAsCategoryList.apply {
                sortByDescending { it.score }
            }
        val highprobabilityOutput = outputs[0]
        tvOutput.text = highprobabilityOutput.label
        Log.i("TAG","outputGenerator:$highprobabilityOutput")

// Releases model resources if no longer used.
        BirdsModel.close()
    }


    // to download image to device
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted: Boolean ->
        if (isGranted){
            AlertDialog.Builder(this).setTitle("Download Image?")
                .setMessage("Do you want to download this image to your device?")
                .setPositiveButton("Yes"){_, _ ->
                    val drawable:BitmapDrawable = imageViewC.drawable as BitmapDrawable
                    val bitmap = drawable.bitmap
                    downloadImage(bitmap)
                }
                .setNegativeButton("No") {dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }else {
            Toast.makeText(this, "Please allow permission to download image", Toast.LENGTH_LONG).show()
        }
    }

    //fun that takes a bitmap and store to user's device
    private fun downloadImage(mBitmap: Bitmap):Uri? {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME,"Birds_Images"+ System.currentTimeMillis()/1000)
            put(MediaStore.Images.Media.MIME_TYPE,"image/png")
        }
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        if (uri != null){
            contentResolver.insert(uri, contentValues)?.also {
                contentResolver.openOutputStream(it).use { outputStream ->
                    if (!mBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)){
                        throw IOException("Couldn't save the bitmap")
                    }
                    else{
                        Toast.makeText(applicationContext, "Image Saved", Toast.LENGTH_LONG).show()
                    }
                }
                return it
            }
        }
        return null
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    //    private fun replaceFragment(fragment: Fragment, title: String){
//        val fragmentManager = supportFragmentManager
//        val fragmentTrnsaction = fragmentManager.beginTransaction()
//        fragmentTrnsaction.replace(R.id.frame_layout,fragment)
//        fragmentTrnsaction.commit()
//        drawerLayout.closeDrawers()
//        setTitle(title)
//    }
    private fun shareImage(bitmap: Bitmap) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"
        val bmpUri: Uri?
        val textToShare = "\nhttps://www.youtube.com/watch?v=-ppp5DbqPLw\n Download the \"FLY-AWAY...\" app in you android device to spread the knowledge of birds whenever you want to your loved ones. Share the app to your friends and family, so that everyone can realize how beautiful the world of birds is !!"
        bmpUri = CameraActivity.saveImage(bitmap, applicationContext)
        share.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        share.putExtra(Intent.EXTRA_STREAM, bmpUri)
        share.putExtra(Intent.EXTRA_SUBJECT, "new App")
        share.putExtra(Intent.EXTRA_TEXT, textToShare)
        startActivity(Intent.createChooser(share, "Share Content"))
    }
    companion object {
        private fun saveImage(image: Bitmap, context: Context): Uri? {
            val imagesfolder = File(context.cacheDir, "images")
            var uri: Uri? = null
            try {
                imagesfolder.mkdirs()
                val file = File(imagesfolder, "shared_images.jpg")
                val stream = FileOutputStream(file)
                image.compress(Bitmap.CompressFormat.JPEG, 90, stream)
                stream.flush()
                stream.close()
                uri = FileProvider.getUriForFile(
                    Objects.requireNonNull(context.applicationContext),
                    "com.example.get_set_go" + ".provider",
                    file
                )
            } catch (e: IOException) {
                Log.d("TAG", "Exception" + e.message)
            }
            return uri
        }
    }

}

