package com.example.lesson_16.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_16.MainViewModel
import com.example.lesson_16.R
import com.example.lesson_16.mainfragment.adapter.ViewPagerAdapter
import com.example.lesson_16.databinding.ActivityWelcomeBinding
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_16.ui.reg.LoginFragment

class WelcomeActivity : AppCompatActivity() {

    private val binding by viewBinding<ActivityWelcomeBinding>(CreateMethod.INFLATE)
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupViewPager()
    }

    private fun setupViewPager() {
        val viewPager = binding.viewPager
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        viewPager.setCurrentItem(0, false)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setCurrentPage(position)


                if (position == adapter.itemCount - 1) {
                    viewPager.post {
                        navigateToFragment(WelcomeFragment())
                    }
                }
            }
        })
    }

    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
