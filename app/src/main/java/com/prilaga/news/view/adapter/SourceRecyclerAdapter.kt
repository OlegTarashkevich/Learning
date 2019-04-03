package com.prilaga.news.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prilaga.news.R
import com.prilaga.news.data.network.model.Source
import com.prilaga.news.view.widget.SourceCardView

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

class SourceRecyclerAdapter : BaseRecyclerAdapter<Source.Entry, SourceRecyclerAdapter.SourceViewHolder>() {

    private val DEFAULT_POSITION = -1
    private var selectedSource = DEFAULT_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_source, parent, false))
    }

    override fun onBindViewHolder(holder: SourceViewHolder, entry: Source.Entry, position: Int) {
        holder.cardView.setSource(entry)
        holder.cardView.isSelected = selectedSource == position
        holder.cardView.cardSelection = object : SourceCardView.CardSelection {
            override fun onSelected(selectedEntry: Source.Entry) {
                // deselect view
                notifyItemChanged(selectedSource)
                selectedSource = position
                // select view
                notifyItemChanged(selectedSource)
            }
        }
    }

    override fun setData(data: List<Source.Entry>?) {
        selectedSource = DEFAULT_POSITION
        super.setData(data)
    }

    class SourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: SourceCardView = itemView.findViewById(R.id.source_card_view)
    }


}