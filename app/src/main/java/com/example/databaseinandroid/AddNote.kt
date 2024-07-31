package com.example.databaseinandroid

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.util.Date

class AddNote : Fragment() {
    private lateinit var notesDB:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notesDB = DBHelper(requireContext())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_note, container, false)
        val title = view.findViewById<EditText>(R.id.title)
        val content = view.findViewById<EditText>(R.id.content)
        val date = view.findViewById<TextView>(R.id.date)
        date.text = LocalDate.now().toString()
        arguments?.let {
            title.setText(it.getString("title"))
            content.setText(it.getString("content"))
            date.setText(it.getString("date"))
        }
        view.findViewById<ImageButton>(R.id.backNavigator).setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        view.findViewById<ImageButton>(R.id.save).setOnClickListener {
            notesDB.insert(Notes(title.text.toString(),content.text.toString(),LocalDate.now().toString(),LocalDate.now().toString(),0))
            println((Notes(title.text.toString(),content.text.toString(),LocalDate.now().toString(),LocalDate.now().toString(),0)).toString())
            parentFragmentManager.popBackStack()
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        println("On Fragment Destroy")
    }
}