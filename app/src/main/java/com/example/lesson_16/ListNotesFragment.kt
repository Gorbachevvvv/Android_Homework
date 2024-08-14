package com.example.lesson_16

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lesson_16.databinding.FragmentListNotesBinding

class ListNotesFragment : Fragment() {

    private var _binding: FragmentListNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListView()

        binding.addNoteView.setOnClickListener {
            (activity as? WelcomeActivity)?.navigateToFragment(NoteFragment())
        }
        binding.logoutTextView.setOnClickListener {
            (activity as? WelcomeActivity)?.navigateToFragment(LoginFragment())
        }
    }

    private fun setupListView() {
        val notes = NoteSingleton.getNotes()
        val adapter = NoteAdapter(requireContext(), notes)
        binding.listViewNotes.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        setupListView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
