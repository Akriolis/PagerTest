package com.akrio.pagertest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.akrio.pagertest.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var callback: OnPageChangeCallback
//    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        toolbar = binding.appBar
//
//        setSupportActionBar(toolbar)
//        callback = CallbackForPager()
        callback = object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Toast.makeText(
                    this@MainActivity,
                    (position + 1).toString() + " " + getString(R.string.fragment),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        viewPager = binding.viewPager
        viewPager.adapter = ViewPagerAdapter(this)
        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val text = when (position) {
                0 -> "first"
                1 -> "second"
                else -> "third"
            }
            tab.text = text
        }.attach()

        viewPager.registerOnPageChangeCallback(callback)

//        supportFragmentManager.beginTransaction()
//            .add(FirstFragment.newInstance(),"FirstFragment")
//            .addToBackStack("FirstFragment")
//            .commitNow()


    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager.unregisterOnPageChangeCallback(callback)
    }



    //    inner class CallbackForPager : OnPageChangeCallback() {
//
//        override fun onPageSelected(position: Int) {
//            Toast.makeText(
//                this@MainActivity,
//                (position + 1).toString() + " " + getString(R.string.fragment),
//                Toast.LENGTH_SHORT
//            )
//                .show()
//        }
//
//    }

}