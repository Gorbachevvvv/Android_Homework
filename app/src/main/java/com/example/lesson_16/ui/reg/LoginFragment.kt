package com.example.lesson_16.ui.reg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lesson_16.databinding.FragmentLoginBinding
import com.example.lesson_16.ui.WelcomeActivity
import com.example.lesson_16.ui.notes.ListNotesFragment
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_16.MainViewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.loginButton.setOnClickListener {
            if (validateInput()) {
                val username = binding.emailEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                if (viewModel.loginUser(username, password)) {
                    (activity as? WelcomeActivity)?.navigateToFragment(ListNotesFragment())
                } else {
                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.returntext.setOnClickListener {
            (activity as? WelcomeActivity)?.navigateToFragment(SignUpFragment())
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

