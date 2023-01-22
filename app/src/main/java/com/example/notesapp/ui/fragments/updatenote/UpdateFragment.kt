package com.example.notesapp.ui.fragments.updatenote

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentUpdateBinding
import com.example.notesapp.model.UserNotes
import com.example.notesapp.viewmodel.NotesViewModel
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment(R.layout.fragment_update) {

    private lateinit var binding : FragmentUpdateBinding
    private val notesViewModel : NotesViewModel by viewModels()

    private lateinit var noteData : UserNotes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)
        setHasOptionsMenu(true)

        getData()

        val button = activity?.findViewById<MaterialButton>(R.id.action_button)
        button?.visibility = View.VISIBLE
        button?.text = "Update"
        button?.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val body = binding.bodyEditText.text.toString()

            saveNote(title,body)
        }

    }

    private fun getData() {
        val data = arguments?.getString("nextData")
        if (data != null){
            noteData = Gson().fromJson(data,UserNotes::class.java)
            setView()
        }
        else{
            Log.d("noteData","No Data........")
        }
    }

    private fun setView() {
        binding.titleEditText.setText(noteData.title)
        binding.bodyEditText.setText(noteData.body)
    }

    private fun saveNote(title: String, body: String) {

        if (title.isNullOrBlank() && body.isNullOrBlank()){
            Toast.makeText(requireContext(),"Empty..." , Toast.LENGTH_SHORT ).show()
        }else{
            val note = UserNotes(noteData.id,title,body,null)
            notesViewModel.updateNote(note)
            Toast.makeText(requireContext(),"Note Updated" , Toast.LENGTH_SHORT ).show()
            val button = activity?.findViewById<MaterialButton>(R.id.action_button)
            button?.visibility = View.GONE
            findNavController().navigate(R.id.action_updateFragment_to_FirstFragment)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                deleteNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->
            notesViewModel.deleteNote(noteData)
            findNavController().navigate(R.id.action_updateFragment_to_FirstFragment)
        }
        builder.setNegativeButton("No"){ _,_ ->

        }
        builder.setTitle("Delete Note !")
        builder.setMessage("Are you sure want to delete this Note ? ")
        builder.create().show()
    }
}