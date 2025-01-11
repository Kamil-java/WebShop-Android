package com.kamil.bak.webshop

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductFragment : Fragment(R.layout.fragment_product) {

    private val productList = listOf(
        Product(1, "Produkt 1", 29.99, "image_url_1"),
        Product(2, "Produkt 2", 49.99, "image_url_2"),
        Product(3, "Produkt 3", 99.99, "image_url_3"),
        Product(4, "Produkt 4", 19.99, "image_url_4"),
        Product(5, "Produkt 5", 39.99, "image_url_5")
    )

    private val cartItems = mutableListOf<Product>()

    private lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ProductAdapter(productList) { product ->
            cartItems.add(product)
            Toast.makeText(requireContext(), "${product.name} dodany do koszyka", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter

        val btnGoToCart: Button = view.findViewById(R.id.btnGoToCart)
        btnGoToCart.setOnClickListener {
            val bundle = Bundle()
            bundle.putParcelableArrayList("cartItems", ArrayList(cartItems))  // Przekazujemy produkty do Bundle
            findNavController().navigate(R.id.action_productFragment_to_cartFragment, bundle)
        }
    }
}








