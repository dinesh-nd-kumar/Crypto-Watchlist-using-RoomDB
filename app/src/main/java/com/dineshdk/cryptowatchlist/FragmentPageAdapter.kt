package com.dineshdk.cryptowatchlist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentPageAdapter(
    supportFragmentManager: FragmentManager,
    lifecycle: Lifecycle)
    : FragmentStateAdapter(supportFragmentManager,lifecycle) {


    override fun getItemCount(): Int {

        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 ->  BlankFragment1()
            1 -> BlankFragment1()
            else -> BlankFragment1()
        }
    }
}