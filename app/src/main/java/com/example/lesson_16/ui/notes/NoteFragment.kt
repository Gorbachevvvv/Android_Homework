package com.example.lesson_16.ui.notes

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lesson_16.MainViewModel
import com.example.lesson_16.data.Note
import com.example.lesson_16.databinding.FragmentNoteBinding
import com.example.lesson_16.ui.WelcomeActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private var datePicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        binding.backTextView.setOnClickListener {
            (activity as? WelcomeActivity)?.navigateToFragment(ListNotesFragment())
        }

        binding.addButton.setOnClickListener {
            addNote()
        }

        binding.datePicker.init(
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ) { _, _, _, _ ->
            datePicked = true
        }
    }

    private fun addNote() {
        val title = binding.titleEditText.text.toString()
        val message = binding.messageEditText.text.toString()
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(message)) {
            Toast.makeText(context, "Both fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val calendar = Calendar.getInstance()
        if (datePicked) {
            val day = binding.datePicker.dayOfMonth
            val month = binding.datePicker.month
            val year = binding.datePicker.year
            calendar.set(year, month, day)
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = dateFormat.format(calendar.time)

        val note = Note(
            title = title,
            message = message,
            date = date
        )

        viewModel.addNote(note)
        Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()

        (activity as? WelcomeActivity)?.navigateToFragment(ListNotesFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
