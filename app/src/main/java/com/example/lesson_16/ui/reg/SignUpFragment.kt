package com.example.lesson_16.ui.reg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lesson_16.databinding.FragmentSignUpBinding
import com.example.lesson_16.ui.WelcomeActivity
import com.example.lesson_16.ui.notes.ListNotesFragment
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_16.MainViewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.regButton.setOnClickListener {
            if (validateInput()) {
                val username = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                if (viewModel.registerUser(username, password)) {
                    (activity as? WelcomeActivity)?.navigateToFragment(ListNotesFragment())
                } else {
                    Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.textView2.setOnClickListener {
            (activity as? WelcomeActivity)?.navigateToFragment(LoginFragment())
        }
    }

    private fun validateInput(): Boolean {
        val username = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        return username.isNotEmpty() && password.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

