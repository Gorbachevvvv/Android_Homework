package com.example.lesson_16.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson_16.R
import com.example.lesson_16.databinding.FragmentSecondPageBinding
import com.example.lesson_16.ui.WelcomeActivity
import com.example.lesson_16.ui.WelcomeFragment

class SecondPageFragment : Fragment(R.layout.fragment_second_page) {

    private var _binding: FragmentSecondPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.Skip.setOnClickListener {
            (activity as? WelcomeActivity)?.navigateToFragment(WelcomeFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
