package com.dineshdk.cryptowatchlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dineshdk.cryptowatchlist.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val adapter  = FragmentPageAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter



        TabLayoutMediator(binding.taplayout,binding.viewPager){ tab , pos ->
            tab.text = "tab ${pos+1}"
            tab.icon = getDrawable(R.drawable.ic_launcher_foreground)
        }.attach()


    }
}