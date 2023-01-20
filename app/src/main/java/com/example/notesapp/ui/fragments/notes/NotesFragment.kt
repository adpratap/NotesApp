package com.example.notesapp.ui.fragments.notes

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.ui.adapters.NotesAdapter
import com.example.notesapp.viewmodel.NotesViewModel
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
        builder.setTitle("Delete All Notes!")
        builder.setMessage("Are you sure want to delete All Notes ? ")
        builder.create().show()
    }
}