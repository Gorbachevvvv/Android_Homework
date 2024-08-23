package com.example.lesson_16.mainfragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lesson_16.ui.pages.FirstPageFragment
import com.example.lesson_16.ui.pages.SecondPageFragment
import com.example.lesson_16.ui.pages.ThirdPageFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = listOf(
        FirstPageFragment(),
        SecondPageFragment(),
        ThirdPageFragment(),
        ThirdPageFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
