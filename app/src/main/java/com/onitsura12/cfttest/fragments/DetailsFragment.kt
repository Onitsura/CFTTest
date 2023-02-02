package com.onitsura12.cfttest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.onitsura12.cfttest.R
import com.onitsura12.cfttest.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {



    private val viewModel: DetailsViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        parentFragmentManager.setFragmentResultListener(
            "cardId", viewLifecycleOwner
        ) { card, bundle ->
            viewModel.getInfo(bundle.getLong("bundle"))
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }








    private fun initViews(){
        binding.apply {
            backButton.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()

                viewModel.cardDetails.observe(viewLifecycleOwner){
                    cardCountry.text = it.country?.name
                }
            }
        }
    }






    companion object {
        fun newInstance() = DetailsFragment()
    }

}