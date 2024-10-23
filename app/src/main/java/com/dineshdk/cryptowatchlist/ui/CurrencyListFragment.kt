package com.dineshdk.cryptowatchlist.ui

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.dineshdk.cryptowatchlist.model.Currency

open class CurrencyListFragment:Fragment() {

    fun showAddToFavoriteDialog(c:Currency,
                                positiveAction:()->Unit,
                                ){
        if (c.isFavorite){
            Toast.makeText(requireContext(), "Already Added", Toast.LENGTH_SHORT).show()

        }else {
            val builder = AlertDialog.Builder(requireContext()).apply {
                setTitle(c.name)
                setIcon(c.imagePath)
                setMessage("Are you want add ${c.name} to favorite?")
                setPositiveButton("Yes") { dialog, which ->
                    positiveAction()
                }
                setNegativeButton("Cancel") { dialog, which ->

                }
            }


            val dialog = builder.create()
            dialog.show()
        }

    }

}