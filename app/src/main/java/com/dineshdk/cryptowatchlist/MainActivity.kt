package com.dineshdk.cryptowatchlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dineshdk.cryptowatchlist.databinding.ActivityMainBinding
import com.dineshdk.cryptowatchlist.model.WatchListViewModel
import com.dineshdk.cryptowatchlist.ui.InrFragment
import com.dineshdk.cryptowatchlist.ui.adapter.FragmentPageAdapter
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
            when(pos) {
                0 -> {
                    tab.text = "Favorite"
                    tab.icon = getDrawable(R.drawable.icons_star_32)
                }
                1 -> {
                    tab.text = "INR"
                    tab.icon = getDrawable(R.drawable.icons_rupee2)
                }
                2 -> {
                    tab.text = "USDT"
                    tab.icon = getDrawable(R.drawable.icons_tether)
                }
                3 -> {
                    tab.text = "UPRO"
                    tab.icon = getDrawable(R.drawable.upro)
                }
                else -> InrFragment()
            }

        }.attach()


    }

    override fun onStart() {
        super.onStart()
        val watchlistViewModel = ViewModelProvider(this).get(WatchListViewModel::class.java)
        watchlistViewModel!!.loadData(this)

    }
}