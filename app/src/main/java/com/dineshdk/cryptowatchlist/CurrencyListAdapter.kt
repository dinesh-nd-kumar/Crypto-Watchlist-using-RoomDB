package com.dineshdk.cryptowatchlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
//                productnameTextview.text = product.name
//                priceTextview.text = "₹ ${product.price}"
//                ratingTextview.text = "${product.avg} (${product.soldcount})"
//                Glide.with(productImage)
//                    .load(currency.imagePath.square)
//                    .fitCenter()
//                    .into(productImage)
                root.setOnClickListener {
                    clickListener.onItemClick(currency)
                }
            }
        }
    }



    public interface ItemClickListener{
        public fun onItemClick(p: Currency)
    }


}