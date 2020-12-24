package com.udacity.shoestore.screens.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentOnboardingInstructionsBinding

class OnboardingInstructionsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOnboardingInstructionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_onboarding_instructions, container, false
        )

        binding.seeModelsButton.setOnClickListener { v: View ->
            v.findNavController()
                .navigate(
                    OnboardingInstructionsFragmentDirections.actionOnboardingInstructionsFragmentToShoeListFragment()
                )
        }

        return binding.root
    }

}