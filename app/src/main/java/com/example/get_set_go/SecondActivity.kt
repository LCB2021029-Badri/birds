package com.example.get_set_go

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.get_set_go.databinding.ActivitySecondBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class SecondActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    // for logout
    lateinit var binding: ActivitySecondBinding
    lateinit var user : FirebaseAuth

    // for image text sharing
    lateinit var imageView: ImageView

    //for rating
    private var reviewInfo: ReviewInfo? = null
    private var manager: ReviewManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val uriSecondActivity = Uri.fromParts("package",packageName,null) // unable to use this in fragments
//        val uri = Uri.fromParts("package",packageName,null)
//        import methods/objects from activities to fragments using = "(activity as SecondActivity?)!!.packageName"

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

        replaceFragment(HomepageFragment(),"Home")

        navView.setNavigationItemSelectedListener {
            it.isChecked = true   // changes color when we click on "nav_menu item"
            when(it.itemId){
                R.id.nav_drawer_home -> {
                    Toast.makeText(applicationContext,"Clicked Home", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,SecondActivity::class.java))
                }
                R.id.nav_drawer_cam -> {
                    Toast.makeText(applicationContext,"Clicked Get Started !", Toast.LENGTH_SHORT).show()
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment, title: String){
        val fragmentManager = supportFragmentManager
        val fragmentTrnsaction = fragmentManager.beginTransaction()
        fragmentTrnsaction.replace(R.id.frame_layout,fragment)
        fragmentTrnsaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }

    //for sharing image and text
    private fun shareImage(bitmap: Bitmap) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "image/jpeg"
        val bmpUri: Uri?
        val textToShare = "\nhttps://www.youtube.com/watch?v=-ppp5DbqPLw\n Download the \"FLY-AWAY...\" app in you android device to spread the knowledge of birds whenever you want to your loved ones. Share the app to your friends and family, so that everyone can realize how beautiful the world of birds is !!"
        bmpUri = saveImage(bitmap, applicationContext)
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

//    //for rating
//    fun activateReviewInfo() {
//        manager = ReviewManagerFactory.create(this)
//        val managerInfoTask = manager!!.requestReviewFlow()
//        managerInfoTask.addOnCompleteListener { task: Task<ReviewInfo?> ->
//            if (task.isSuccessful) {
//                reviewInfo = task.result
//            } else {
//                Toast.makeText(this, "Review failed to start", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//    fun startReviewFlow() {
//        if (reviewInfo != null) {
//            val flow = manager!!.launchReviewFlow(this, reviewInfo!!)
//            flow.addOnCompleteListener { task: Task<Void?>? ->
//                Toast.makeText(
//                    this,
//                    "Rating is completed",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }

}