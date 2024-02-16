package com.example.notesapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.noreplypratap.domain.model.DomainNotes

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        val bodyTextView: TextView = itemView.findViewById(R.id.body_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bodyTextView.text = item.body
        holder.titleTextView.text = item.title

        holder.itemView.setOnClickListener {
            onItemClicked?.let {
                it(item)
            }
        }
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<DomainNotes>(){
        override fun areItemsTheSame(oldItem: DomainNotes, newItem: DomainNotes): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: DomainNotes, newItem: DomainNotes): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffCallBack)
    private var onItemClicked : ((DomainNotes) -> Unit)? = null

    fun setOnClickListener(listener : (DomainNotes) -> Unit){
        onItemClicked = listener
    }
}