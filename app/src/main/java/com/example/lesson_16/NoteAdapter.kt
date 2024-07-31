package com.example.lesson_16

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast

class NoteAdapter(private val context: Context, private val notes: List<Note>) : BaseAdapter() {

    override fun getCount(): Int {
        return notes.size
    }

    override fun getItem(position: Int): Any {
        return notes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.item_note, parent, false)
        } else {
            view = convertView
        }

        val note = getItem(position) as Note

        val tvNoteTitle: TextView = view.findViewById(R.id.tvNoteTitle)
        val tvNoteMessage: TextView = view.findViewById(R.id.tvNoteMessage)
        val tvNoteDate: TextView = view.findViewById(R.id.tvNoteDate)

        tvNoteTitle.text = note.title
        tvNoteMessage.text = note.message
        tvNoteDate.text = note.date

        tvNoteTitle.setOnClickListener {
            Toast.makeText(context, note.message, Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
