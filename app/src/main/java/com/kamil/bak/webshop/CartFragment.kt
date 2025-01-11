package com.kamil.bak.webshop

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var adapter: CartAdapter
    private var cartItems: MutableList<Product> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        bundle?.let {
            val items = it.getParcelableArrayList<Product>("cartItems")
            if (items != null) {
                cartItems.addAll(items)
            }
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CartAdapter(cartItems) { product ->
            cartItems.remove(product)
            adapter.notifyDataSetChanged()
            Toast.makeText(
                requireContext(),
                "${product.name} usunięty z koszyka",
                Toast.LENGTH_SHORT
            ).show()
        }

        recyclerView.adapter = adapter
    }
}










