package com.example.madlevel4task1.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task1.R
import com.example.madlevel4task1.model.Product
import com.example.madlevel4task1.repository.ProductRepository
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ShoppingListFragment : Fragment() {

    private lateinit var productRepository: ProductRepository

    private val shoppingList = arrayListOf<Product>()
    private val productAdapter =
        ShoppingListAdapter(shoppingList)
    private val mainScope = CoroutineScope(Dispatchers.Main)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRepository =
            ProductRepository(
                requireContext()
            )
        getShoppingListFromDatabase()

        initRecyclerView()

        addToShoppingListButton.setOnClickListener{
            didTappedAddProductToList()
        }

        removeAllProducts.setOnClickListener {
            didTappedDeleteButton()
        }

    }

    private fun initRecyclerView() {
        shoppingListRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        shoppingListRecyclerView.adapter = productAdapter
        shoppingListRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(shoppingListRecyclerView)

    }

    private fun createItemTouchHelper() : ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val productToDelete = shoppingList[position]

                mainScope.launch {
                    withContext(Dispatchers.IO) {
                        productRepository.deleteProduct(productToDelete)
                    }
                    getShoppingListFromDatabase()
                }
            }
        }

        return ItemTouchHelper(callback)
    }

    @SuppressLint("InflateParams")
    private fun didTappedAddProductToList() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.app_name))
        val dialogLayout = layoutInflater.inflate(R.layout.add_product_dialog, null)
        val productName = dialogLayout.findViewById<EditText>(R.id.productNameText)
        val productAmount = dialogLayout.findViewById<EditText>(R.id.amountText)

        builder.setView(dialogLayout)
        builder.setPositiveButton(R.string.dialog_ok_btn) { _: DialogInterface, _: Int ->
            addProduct(productName, productAmount)
        }
        builder.show()
    }

    private fun didTappedDeleteButton() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                productRepository.deleteAllProduct()
            }
            getShoppingListFromDatabase()
        }
    }

    private fun addProduct(productName: EditText, amount: EditText) {

        if (validateFields(productName, amount)) {
            mainScope.launch {
                val product = Product(
                    productName = productName.text.toString(),
                    productAmount = amount.text.toString().toShort()
                )

                withContext(Dispatchers.IO) {
                    productRepository.addProductToList(product)
                }

                getShoppingListFromDatabase()
            }
        }


    }

    private fun validateFields(productName: EditText, amountText: EditText): Boolean {

        return if (productName.text.toString().isNotBlank()
            && amountText.text.toString().isNotBlank()
        ) {
            true
        } else {
            Toast.makeText(activity, "Please fill in the fields", Toast.LENGTH_LONG).show()
            false
        }
    }


    private fun getShoppingListFromDatabase() {
        mainScope.launch {
            val shoppingList = withContext(Dispatchers.IO) {
                productRepository.getAllProducts()
            }
            this@ShoppingListFragment.shoppingList.clear()
            this@ShoppingListFragment.shoppingList.addAll(shoppingList)
            this@ShoppingListFragment.productAdapter.notifyDataSetChanged()
        }
    }
}


