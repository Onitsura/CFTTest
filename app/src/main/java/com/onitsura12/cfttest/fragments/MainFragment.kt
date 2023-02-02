package com.onitsura12.cfttest.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.onitsura12.cfttest.R
import com.onitsura12.cfttest.databinding.ActivityMainBinding
import com.onitsura12.cfttest.databinding.FragmentMainBinding
import com.onitsura12.cfttest.recyclerView.DetailsAdapter

class MainFragment : Fragment() {


    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding
    private val adapter: DetailsAdapter = DetailsAdapter()

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
        viewModel.getList()

    }


    private fun initViews() {
        binding.apply {

            viewModel.historyList.observe(viewLifecycleOwner) {
                adapter.submitList(it.toMutableList().reversed())
            }


            checkButton.setOnClickListener {
                if (etInputNumber.text.length == 8) {
                    val id: Long = etInputNumber.text.toString().toLong()

                    setFragmentResult("cardId", bundleOf("bundle" to id))
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, DetailsFragment.newInstance())
                        .addToBackStack("Names")
                        .commit()
                }
                else Toast.makeText(requireContext(), "Необходимо ввести 8 первых цифр карты",
                    Toast.LENGTH_SHORT).show()
            }
            historyRecycler.layoutManager = LinearLayoutManager(requireContext())
            historyRecycler.adapter = adapter
        }
    }


    companion object {
        fun newInstance() = MainFragment()
    }


}