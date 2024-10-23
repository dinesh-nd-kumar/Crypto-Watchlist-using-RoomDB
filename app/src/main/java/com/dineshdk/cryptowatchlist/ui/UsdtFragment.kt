package com.dineshdk.cryptowatchlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.dineshdk.cryptowatchlist.databinding.FragmentUsdtBinding
import com.dineshdk.cryptowatchlist.model.Currency
import com.dineshdk.cryptowatchlist.model.WatchListViewModel
import com.dineshdk.cryptowatchlist.ui.adapter.CurrencyListAdapter


class UsdtFragment : Fragment(),CurrencyListAdapter.ItemClickListener {

    private var _binding: FragmentUsdtBinding? = null
    private val binding get() = _binding!!

    private var watchlistViewModel: WatchListViewModel? = null

    private lateinit var currencyAdapter: CurrencyListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsdtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        watchlistViewModel = ViewModelProvider(requireActivity()).get(WatchListViewModel::class.java)
        watchlistViewModel!!.getUSDTCurrencyLiveData().observe(viewLifecycleOwner){
            setRecycler(it)

        }
    }

    private fun setRecycler(list: List<Currency>?){
        currencyAdapter.currencyList = list
        currencyAdapter.notifyDataSetChanged()
    }

    private fun initRecycler(){
        binding.rvCurrencies.apply {
            currencyAdapter = CurrencyListAdapter(this@UsdtFragment,null)
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
//        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(c: Currency) {
        val builder = AlertDialog.Builder(requireContext()).apply {
            setTitle(c.name)
            setIcon(c.imagePath)
            setMessage("Are you want add ${c.name} to favorite?")
            setPositiveButton("Yes") { dialog, which ->
                c.isFavorite = true
                watchlistViewModel!!.updateCurrencyToFavorite(c)
            }
            setNegativeButton("Cancel") { dialog, which ->

            }
        }


        val dialog = builder.create()
        dialog.show()

    }
}