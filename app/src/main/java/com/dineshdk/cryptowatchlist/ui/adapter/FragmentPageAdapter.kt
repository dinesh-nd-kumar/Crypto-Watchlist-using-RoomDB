package com.dineshdk.cryptowatchlist.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dineshdk.cryptowatchlist.ui.InrFragment
import com.dineshdk.cryptowatchlist.ui.FavoriteFragment
import com.dineshdk.cryptowatchlist.ui.UproFragment
import com.dineshdk.cryptowatchlist.ui.UsdtFragment

class FragmentPageAdapter(
    supportFragmentManager: FragmentManager,
    lifecycle: Lifecycle)
    : FragmentStateAdapter(supportFragmentManager,lifecycle) {


    override fun getItemCount(): Int {

        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->  FavoriteFragment()
            1 -> InrFragment()
            2 -> UsdtFragment()
            3 -> UproFragment()
            else -> InrFragment()
        }
    }
}