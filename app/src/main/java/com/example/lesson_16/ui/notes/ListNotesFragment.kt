package com.example.lesson_16.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_16.MainViewModel
import com.example.lesson_16.mainfragment.adapter.NoteAdapter
import com.example.lesson_16.databinding.FragmentListNotesBinding
import com.example.lesson_16.ui.WelcomeActivity
import com.example.lesson_16.ui.reg.LoginFragment

class ListNotesFragment : Fragment() {

    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        setupListView()

        binding.addNoteView.setOnClickListener {
            (activity as? WelcomeActivity)?.navigateToFragment(NoteFragment())
        }

        binding.logoutTextView.setOnClickListener {
            (activity as? WelcomeActivity)?.navigateToFragment(LoginFragment())
        }

        viewModel.notes.observe(viewLifecycleOwner) {
            setupListView()
        }
    }

    private fun setupListView() {
        val notes = viewModel.notes.value ?: emptyList()
        val adapter = NoteAdapter(requireContext(), notes)
        binding.listViewNotes.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
