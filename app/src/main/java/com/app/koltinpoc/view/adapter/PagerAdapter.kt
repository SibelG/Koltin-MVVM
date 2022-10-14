package com.app.koltinpoc.view.adapter

import android.provider.SyncStateContract
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.koltinpoc.utils.Constants
import com.app.koltinpoc.utils.Constants.SPORT
import com.app.koltinpoc.utils.Constants.TECH
import com.app.koltinpoc.view.fragments.HomeFragment

class PagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    /*override fun createFragment(position: Int): Fragment {
       Constants.apply {
            return when (position) {
                0 -> {
                    HomeFragment(ALL)
                }
                1 -> {
                    HomeFragment(HEALTH)
                }
                2 -> {
                    HomeFragment(TECH)
                }
                else -> {
                    HomeFragment(SPORT)
                }
            }
        }
    }*/


    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        TODO("Not yet implemented")
    }
}