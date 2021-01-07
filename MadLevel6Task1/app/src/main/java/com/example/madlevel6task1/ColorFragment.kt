package com.example.madlevel6task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task1.Model.ColorItem
import com.example.madlevel6task1.ui.ColorAdapter
import com.example.madlevel6task1.viewModel.ColorViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_color.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ColorFragment : Fragment() {

    private val colors = arrayListOf<ColorItem>()
    private lateinit var colorAdapter: ColorAdapter
    private val viewModel: ColorViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_color, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeColors()
    }

    private fun observeColors() {
        viewModel.colorItems.observe(viewLifecycleOwner, Observer {
            colors.clear()
            colors.addAll(it)
            colorAdapter.notifyDataSetChanged()
        })
    }

    private fun initView() {

        colorAdapter = ColorAdapter(colors, ::onColorClick)
        rvColors.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvColors.adapter = colorAdapter

    }

    private fun onColorClick(colorItem: ColorItem) {
    Snackbar.make(rvColors, "this color is: ${colorItem.name}", Snackbar.LENGTH_LONG).show()
    }
}