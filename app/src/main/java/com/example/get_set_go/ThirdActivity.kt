package com.example.get_set_go

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.get_set_go.adapter.ItemAdapter
import com.example.get_set_go.data.Datasource
import com.example.get_set_go.databinding.ActivityThirdBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ThirdActivity : AppCompatActivity() {

    lateinit var binding : ActivityThirdBinding
//    lateinit var recyclerView: RecyclerView
//    private val LIST_VIEW = "LIST_VIEW"
//    private val GRID_VIEW = "GRID_VIEW"
//    var currentView = LIST_VIEW
//    val myDataSet = Datasource().loadScroll()


//    lateinit var toggle: ActionBarDrawerToggle
//    lateinit var drawerLayout: DrawerLayout
//    lateinit var user : FirebaseAuth
//    lateinit var imageView: ImageView
lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var user : FirebaseAuth
    lateinit var imageView: ImageView


    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)



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




//        // converting to bitmap
//        imageView = findViewById(R.id.share_app)
//        val bitmapDrawable = imageView.getDrawable() as BitmapDrawable
//        val bitmap = bitmapDrawable.bitmap
//
//        drawerLayout = findViewById(R.id.drawerLayout)
//        val navView: NavigationView = findViewById(R.id.nav_view)

//        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)



//        navView.setNavigationItemSelectedListener {
//            it.isChecked = true   // changes color when we click on "nav_menu item"
//            when(it.itemId){
//                R.id.nav_drawer_home -> {
//                    Toast.makeText(applicationContext,"Clicked Home", Toast.LENGTH_SHORT).show()
////                    replaceFragment(HomepageFragment(),it.title.toString())
//                    startActivity(Intent(this,SecondActivity::class.java))
//                }
//                R.id.nav_drawer_cam -> {
//                    Toast.makeText(applicationContext,"Clicked Get Started !", Toast.LENGTH_SHORT).show()
////                    replaceFragment(CamFragment(),it.title.toString())
//                    startActivity(Intent(this,CameraActivity::class.java))
//                }
//                R.id.nav_drawer_top -> {
//                    Toast.makeText(applicationContext,"Top Searches", Toast.LENGTH_SHORT).show()
////                    replaceFragment(RecommendationsFragment(),it.title.toString())
//                    startActivity(Intent(this,ThirdActivity::class.java))
//                }
//                R.id.nav_drawer_about->{
//                    Toast.makeText(applicationContext,"Clicked View", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this,FourthActivity::class.java))
//                }
//                R.id.nav_drawer_logout -> {
//                    Toast.makeText(applicationContext,"Logged out", Toast.LENGTH_SHORT).show()
//                    user = FirebaseAuth.getInstance()
//                    user.signOut()
//                    startActivity(Intent(this,MainActivity::class.java))
//                    finish()
//                }
//                R.id.nav_drawer_share ->{
//                    Toast.makeText(applicationContext,"Clicked Share", Toast.LENGTH_SHORT).show()
//                    shareImage(bitmap)  // calling created share function
//                }
//                R.id.nav_drawer_rate -> {
//                    Toast.makeText(applicationContext,"Not a Google Developer, So can't give us Rating !!", Toast.LENGTH_SHORT).show()
////                    startReviewFlow() // for rating if we are a developer
//                }
//                R.id.nav_drawer_contact -> {
//                    Toast.makeText(applicationContext,"Contact Us", Toast.LENGTH_SHORT).show()
//                    startActivity(Intent(this,ContactusActivity::class.java))
//                }
//            }
//            true
//        }
        binding.one.setOnClickListener {
            startActivity(Intent(this,StyleSquareActivity::class.java))
        }
        binding.two.setOnClickListener {
            startActivity(Intent(this,StyleDiamondActivity::class.java))
        }
        binding.three.setOnClickListener {
            startActivity(Intent(this,StyleRectangularActivity::class.java))
        }
        binding.four.setOnClickListener {
            startActivity(Intent(this,StyleOvalActivity::class.java))
        }
        binding.five.setOnClickListener {
            startActivity(Intent(this,StyleRoundActivity::class.java))
        }
        binding.six.setOnClickListener {
            startActivity(Intent(this,StyleTriangleActivity::class.java))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }





//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(toggle.onOptionsItemSelected(item)){
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }


//    private fun shareImage(bitmap: Bitmap) {
//        val share = Intent(Intent.ACTION_SEND)
//        share.type = "image/jpeg"
//        val bmpUri: Uri?
//        val textToShare = "\nhttps://youtu.be/46vcC7ho9dM\n Download the \"GET-SET-GO\" app in you android device to set your hairstyle whenever you want. Share the app to your friends and family, so that everyone can look dazzling along with you."
//        bmpUri = ThirdActivity.saveImage(bitmap, applicationContext)
//        share.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        share.putExtra(Intent.EXTRA_STREAM, bmpUri)
//        share.putExtra(Intent.EXTRA_SUBJECT, "new App")
//        share.putExtra(Intent.EXTRA_TEXT, textToShare)
//        startActivity(Intent.createChooser(share, "Share Content"))
//    }
//    companion object {
//        private fun saveImage(image: Bitmap, context: Context): Uri? {
//            val imagesfolder = File(context.cacheDir, "images")
//            var uri: Uri? = null
//            try {
//                imagesfolder.mkdirs()
//                val file = File(imagesfolder, "shared_images.jpg")
//                val stream = FileOutputStream(file)
//                image.compress(Bitmap.CompressFormat.JPEG, 90, stream)
//                stream.flush()
//                stream.close()
//                uri = FileProvider.getUriForFile(
//                    Objects.requireNonNull(context.applicationContext),
//                    "com.example.get_set_go" + ".provider",
//                    file
//                )
//            } catch (e: IOException) {
//                Log.d("TAG", "Exception" + e.message)
//            }
//            return uri
//        }
//    }

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
        bmpUri = ThirdActivity.saveImage(bitmap, applicationContext)
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