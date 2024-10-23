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

    override fun onItemClick(c: Currency) {
        val builder = AlertDialog.Builder(requireContext()).apply {
            setTitle(c.name)
            setIcon(c.imagePath)
            setMessage(" Name: ${c.name} \n Des: ${c.description}")
            setPositiveButton("OK") { dialog, which ->

            }

        }


        val dialog = builder.create()
        dialog.show()






    }


    override fun onItemLongClick(c: Currency) {
        val builder = AlertDialog.Builder(requireContext()).apply {
            setTitle(c.name)
            setIcon(c.imagePath)
            setMessage("Are you want remove ${c.name} from favorite?")
            setPositiveButton("Yes") { dialog, which ->
                c.isFavorite = false
                watchlistViewModel!!.updateCurrencyToFavorite(c)
            }
            setNegativeButton("Cancel") { dialog, which ->

            }
        }


        val dialog = builder.create()
        dialog.show()

    }
}