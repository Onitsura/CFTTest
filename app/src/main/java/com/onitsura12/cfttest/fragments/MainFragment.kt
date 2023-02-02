package com.onitsura12.cfttest.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.onitsura12.cfttest.R
import com.onitsura12.cfttest.databinding.ActivityMainBinding
import com.onitsura12.cfttest.databinding.FragmentMainBinding

class MainFragment : Fragment() {



    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }



    private fun initViews(){
        binding.apply {

            checkButton.setOnClickListener {
                val id: Long = etInputNumber.text.toString().toLong()

                setFragmentResult("cardId", bundleOf("bundle" to id))
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, DetailsFragment.newInstance()).addToBackStack("Names")
                    .commit()


            }




        }
    }




    companion object {
        fun newInstance() = MainFragment()
    }


}