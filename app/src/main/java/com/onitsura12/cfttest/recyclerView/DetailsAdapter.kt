package com.onitsura12.cfttest.recyclerView


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.onitsura12.cfttest.data.models.CardDetails
import com.onitsura12.cfttest.data.models.CardDetailsDatabaseModel
import com.onitsura12.cfttest.databinding.HistoryItemBinding

class DetailsAdapter() :
    ListAdapter<CardDetailsDatabaseModel, DetailsAdapter.ItemHolder>(ItemComparator()) {


    class ItemHolder(private val binding: HistoryItemBinding) : RecyclerView.ViewHolder(
        binding
            .root
    ) {
        fun bind(cardDetails: CardDetailsDatabaseModel) {
            binding.apply {
                cardBrand.text = cardDetails.brand.toString()
                cardPrepaid.text = cardDetails.prepaid.toString()
                cardScheme.text = cardDetails.scheme.toString()
                cardType.text = cardDetails.type.toString()
                number.text = cardDetails.number.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            HistoryItemBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ItemComparator : DiffUtil.ItemCallback<CardDetailsDatabaseModel>() {
    override fun areItemsTheSame(
        oldItem: CardDetailsDatabaseModel,
        newItem: CardDetailsDatabaseModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CardDetailsDatabaseModel,
        newItem: CardDetailsDatabaseModel
    ): Boolean {
        return oldItem.number == newItem.number
    }

}