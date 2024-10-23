package com.dineshdk.cryptowatchlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.dineshdk.cryptowatchlist.ui.adapter.CurrencyListAdapter
import com.dineshdk.cryptowatchlist.databinding.FragmentBlank1Binding
import com.dineshdk.cryptowatchlist.model.Currency
import com.dineshdk.cryptowatchlist.model.WatchListViewModel



class InrFragment : CurrencyListFragment(), CurrencyListAdapter.ItemClickListener {


    private var _binding: FragmentBlank1Binding? = null
    private val binding get() = _binding!!

    private var watchlistViewModel: WatchListViewModel? = null

    private lateinit var currencyAdapter: CurrencyListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlank1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        watchlistViewModel = ViewModelProvider(requireActivity()).get(WatchListViewModel::class.java)
        watchlistViewModel!!.getINRCurrencyLiveData().observe(viewLifecycleOwner){
            setRecycler(it)

        }
    }

    private fun setRecycler(list: List<Currency>?){
        currencyAdapter.currencyList = list
        currencyAdapter.notifyDataSetChanged()
    }

    private fun initRecycler(){
        binding.rvCurrencies.apply {
            currencyAdapter = CurrencyListAdapter(this@InrFragment,null)
            adapter = currencyAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.HORIZONTAL)
            )

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InrFragment().apply {

            }
    }

    override fun onItemClick(c: Currency) {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(c: Currency) {
        super.showAddToFavoriteDialog(c){
            c.isFavorite = true
            watchlistViewModel!!.updateCurrencyToFavorite(c)
        }

    }
}