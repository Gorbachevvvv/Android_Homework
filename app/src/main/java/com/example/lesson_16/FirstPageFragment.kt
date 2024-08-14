package com.example.lesson_16

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson_16.databinding.FragmentFirstPageBinding
import com.example.lesson_16.databinding.FragmentThirdPageBinding

class FirstPageFragment : Fragment(R.layout.fragment_first_page) {

    private var _binding: FragmentFirstPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstPageBinding.inflate(inflater, container, false)
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
