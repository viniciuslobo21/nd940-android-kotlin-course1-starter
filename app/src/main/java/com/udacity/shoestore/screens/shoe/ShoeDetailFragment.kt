package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()


    private var shoe = Shoe("", 0.0, "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        initButtonsListeners()
        binding.shoe = shoe
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun initButtonsListeners() {
        binding.cancelButton.setOnClickListener { v: View ->
            v.findNavController().navigateUp()
        }

        binding.saveButton.setOnClickListener { v: View ->
            if (shoe.name.isBlank() && shoe.company.isBlank()
                && shoe.size == 0.0 && shoe.description.isBlank()
            ) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.inputs_error),
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                viewModel.saveShoe(shoe)
                v.findNavController().navigateUp()
            }
        }
    }
}