package com.example.notesapp.ui.fragments.notes

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.ui.adapters.NotesAdapter
import com.example.notesapp.viewmodel.NotesViewModel
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesFragment : Fragment() {

    private val notesViewModel : NotesViewModel by viewModels()
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val noteAdapter = NotesAdapter()
        binding.rvNotes.adapter = noteAdapter
        recyclerViewOnClick(noteAdapter)

        notesViewModel.getNotes().observe(viewLifecycleOwner) {
            noteAdapter.differ.submitList(it)
            noteAdapter.notifyDataSetChanged()
        }

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun recyclerViewOnClick(noteAdapter: NotesAdapter) {
        noteAdapter.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("nextData" , Gson().toJson(it))
            findNavController().navigate(R.id.action_FirstFragment_to_updateFragment,bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        val button = activity?.findViewById<MaterialButton>(R.id.action_button)
        button?.visibility = View.GONE
    }
    override fun onResume() {
        super.onResume()
        val button = activity?.findViewById<MaterialButton>(R.id.action_button)
        button?.visibility = View.GONE
    }
    @Deprecated("Deprecated in Java",
        ReplaceWith("inflater.inflate(R.menu.menu_main, menu)", "com.example.notesapp.R")
    )
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                deleteAllData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _,_ ->
            notesViewModel.deleteAllNotes()
        }
        builder.setNegativeButton("No"){ _,_ ->

        }
        builder.setTitle("Delete Everything !")
        builder.setMessage("Are you sure want to delete Everything ? ")
        builder.create().show()
    }
}