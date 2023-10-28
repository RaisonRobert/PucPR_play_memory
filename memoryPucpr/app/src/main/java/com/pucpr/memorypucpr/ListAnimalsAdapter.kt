package com.pucpr.memorypucpr

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAnimalsAdapter(
    private val cards: List<Int>,
    private val cardsSecond: MutableList<Int>,
    private val onSuccess: (firstIdCard: Int) -> Unit,
    private val onFail: (firstIdCard: Int) -> Unit
) :
    RecyclerView.Adapter<ListAnimalsAdapter.AnimalsViewHolder>() {
    private var firstIdCard:Int? = null
    private var secondIdCard:Int? = null
    inner class AnimalsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Int) {
            if(cardsSecond.contains(item)){
                itemView.setBackgroundResource(item)
            }
            item.let {
                itemView.setOnClickListener {
                        itemView.setBackgroundResource(item)
                        onClickCard (item)
                }
            }
        }
    }
    private fun onClickCard(idItem: Int) {
        if (firstIdCard == null) {
            firstIdCard = idItem
        }
        else {
            secondIdCard = idItem
            if (firstIdCard == secondIdCard) {
                onSuccess(firstIdCard!!)
                firstIdCard = null
                secondIdCard = null
            }else{
                onFail(firstIdCard!!)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animals_card, parent, false)
        return AnimalsViewHolder(view)
    }

    override fun getItemCount() = cards.size

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        holder.bind(cards[position])
    }

}
