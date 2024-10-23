package com.dineshdk.cryptowatchlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.dineshdk.cryptowatchlist.ui.adapter.CurrencyListAdapter
import com.dineshdk.cryptowatchlist.databinding.FragmentFavoriteBinding
import com.dineshdk.cryptowatchlist.model.Currency
import com.dineshdk.cryptowatchlist.model.WatchListViewModel


class FavoriteFragment : Fragment(), CurrencyListAdapter.ItemClickListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private var watchlistViewModel: WatchListViewModel? = null

    private lateinit var currencyAdapter: CurrencyListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        watchlistViewModel = ViewModelProvider(requireActivity()).get(WatchListViewModel::class.java)
        watchlistViewModel!!.getAllFavoriteCurrencyLiveData().observe(viewLifecycleOwner){
            setRecycler(it)

        }
    }

    private fun setRecycler(list: List<Currency>?){
        currencyAdapter.currencyList = list
        currencyAdapter.notifyDataSetChanged()
    }

    private fun initRecycler(){
        binding.rvCurrencies.apply {
            currencyAdapter = CurrencyListAdapter(this@FavoriteFragment,null)
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
            FavoriteFragment().apply {

            }
    }

    override fun onItemClick(p: Currency) {
//        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
    }


    override fun onItemLongClick(c: Currency) {
//        Toast.makeText(requireContext(), "long", Toast.LENGTH_SHORT).show()

    }
}