package com.udacity.shoestore.screens.shoe

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.models.Shoe


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    private lateinit var listShoe: MutableList<Shoe>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        initButtonListeners()
        setHasOptionsMenu(true)
        observeChangesViewModel()

        return binding.root
    }

    private fun initButtonListeners() {
        binding.fabDetails.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
    }

    private fun observeChangesViewModel() {
        viewModel.listShoe.observe(this, Observer { list ->
            binding.itemShoe.removeAllViews()
            list.forEach { shoe ->
                val inflater = LayoutInflater.from(binding.itemShoe.context)
                val binding: ItemShoeBinding =
                    ItemShoeBinding.inflate(inflater, binding.itemShoe, true)
                binding.shoe = shoe
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}

@BindingAdapter("android:text")
fun bindDoubleInText(tv: EditText, value: Double) {
    tv.setText(value.toString())
    // Set the cursor to the end of the text
    tv.setSelection(tv.text!!.length)
}

@InverseBindingAdapter(attribute = "android:text")
fun getDoubleFromBinding(view: TextView): Double {
    val string = view.text.toString()
    return if (string.isEmpty()) 0.0 else string.toDouble()
}