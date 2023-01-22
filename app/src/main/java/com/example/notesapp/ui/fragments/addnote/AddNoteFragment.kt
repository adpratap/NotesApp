package com.example.notesapp.ui.fragments.addnote

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentAddNoteBinding
import com.example.notesapp.model.UserNotes
import com.example.notesapp.viewmodel.NotesViewModel
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private val notesViewModel : NotesViewModel by viewModels()
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val button = activity?.findViewById<MaterialButton>(R.id.action_button)
        button?.visibility = View.VISIBLE
        button?.text = "Save"
        button?.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val body = binding.bodyEditText.text.toString()

            saveNote(title,body)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote(title: String, body: String) {

        if (title.isBlank() || body.isEmpty()){
            Toast.makeText(requireContext(),"Empty..." , Toast.LENGTH_SHORT ).show()
        }else{
            val note = UserNotes(0,title,body,null)
            notesViewModel.saveNote(note)
            Toast.makeText(requireContext(),"Note Saved" , Toast.LENGTH_SHORT ).show()
            val button = activity?.findViewById<MaterialButton>(R.id.action_button)
            button?.visibility = View.GONE
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}