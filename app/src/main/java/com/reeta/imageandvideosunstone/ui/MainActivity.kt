package com.reeta.imageandvideosunstone.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.reeta.imageandvideosunstone.R
import com.reeta.imageandvideosunstone.adapter.PagerAdapter

/*
This is our home screen for showing our images and videos, In this screen we are asking for
permission to read device data because read external storage is a dangerous permission
so we have to ask user for this permission. And In this activity I am setting 2 fragments
for showing 2 tabs inside the screen
 */
class MainActivity : AppCompatActivity() {
    lateinit var tab: TabLayout
    lateinit var viewPager2: ViewPager2
    private var STORAGE_PERMISSION = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "My Gallery"
        tab = findViewById(R.id.tabAll)
        viewPager2 = findViewById(R.id.viewPager)
        setPermission()

    }

    /*
    In this method I am asking for read external storage permission, if permissoin will
    denied then request for permission again and if permission will granted the set our
    adapter for viewpager for setting the fragment dynamically
     */
    private fun setPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_PERMISSION
            )
        } else {
            setAdapter()
        }
    }

    /*
    This method for storing permission result, if permission granted the continue with app,
    if permission denied then finish the app not continue ferther
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show()
                setAdapter()
            } else {
                Toast.makeText(this, "The App will not work without permission", Toast.LENGTH_LONG)
                    .show()
                finish()
            }
        }
    }


    /*
    This method will set adapter for our viewPager so we can swip and tab dynamically our
    fragments.
     */
    private fun setAdapter() {
        val pagerAdapter = PagerAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = pagerAdapter
        TabLayoutMediator(tab, viewPager2) { tab, position ->
            if (position == 0) tab.text = "Show Images"
            else tab.text = "Show Videos"
        }.attach()
    }
}