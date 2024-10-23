package com.dineshdk.cryptowatchlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dineshdk.cryptowatchlist.R
import com.dineshdk.cryptowatchlist.databinding.RowItemBinding
import com.dineshdk.cryptowatchlist.model.Currency

class CurrencyListAdapter(
    val clickListener: ItemClickListener,
    var currencyList: List<Currency>?)
    : RecyclerView.Adapter<CurrencyListAdapter.CurrencyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(
            RowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false))
    }

    override fun getItemCount(): Int {
        if (currencyList == null)
            return 0
        return currencyList!!.size
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val currency = currencyList?.get(position)
        if (currency != null) {
            holder.bindData(currency)
        }
    }


    inner class CurrencyViewHolder(val binding: RowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(currency: Currency) {
            binding.apply {
                nameTextView.text = currency.name
                volumeTextView.text = "Vol ${currency.volume} M"
                priceTextView.text = "₹ ${currency.price} "
                iconImageView.setImageResource(currency.imagePath)
                percentageTextView.apply {

                    if (currency.percentage >= 0){
                        setBackgroundResource(R.drawable.bg_green_rounded)
                        text = "+${currency.percentage} %"
                    }
                    else{
                        text = "${currency.percentage} %"
                        setBackgroundResource(R.drawable.bg_white_rounded)
                    }


                }





//                Glide.with(iconImageView).load(currency.imagePath).fitCenter().into(iconImageView)



                root.setOnClickListener {
                    clickListener.onItemClick(currency)
                }
                root.setOnLongClickListener {
                    clickListener.onItemLongClick(currency)
                    return@setOnLongClickListener true
                }
            }
        }
    }



    public interface ItemClickListener{
        public fun onItemClick(c: Currency)
        public fun onItemLongClick(c: Currency)
    }


}