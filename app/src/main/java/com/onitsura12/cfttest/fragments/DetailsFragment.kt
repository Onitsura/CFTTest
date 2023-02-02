package com.onitsura12.cfttest.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.onitsura12.cfttest.R
import com.onitsura12.cfttest.data.Mapper
import com.onitsura12.cfttest.data.models.CardDetailsDatabaseModel
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
            viewModel.number.value = bundle.getLong("bundle")
            viewModel.getInfo()
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }


    private fun initViews() {
        binding.apply {
            backButton.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()

            }
            viewModel.cardDetails.observe(viewLifecycleOwner) {
                val newHistoryItem: CardDetailsDatabaseModel = Mapper.toDatabaseDetails(it)
                newHistoryItem.number = viewModel.number.value.toString()
                cardBrand.text = isNull(it.brand.toString())
                cardPrepaid.text = isNull(it.prepaid.toString())
                cardScheme.text = isNull(it.scheme.toString())
                cardType.text = isNull(it.type.toString())

                val countryEmoji = it.country?.emoji.toString()
                val countryName = it.country?.name.toString()
                val country = "$countryEmoji $countryName"
                cardCountry.text = it.country?.name.toString()
                cardCountryLat.text = isNull(it.country?.latitude.toString())
                cardCountryLong.text = isNull(it.country?.longitude.toString())


                bankCity.text = isNull(it.bank?.city.toString())
                bankName.text = isNull(it.bank?.name.toString())
                bankPhone.text = isNull(it.bank?.phone.toString())
                bankURL.text = isNull(it.bank?.url.toString())
                viewModel.addDetails(newHistoryItem)
            }
        }
    }


    private fun isNull(string: String): String {
        return if (string == "null") "Нет данных"
        else string

    }


    companion object {
        fun newInstance() = DetailsFragment()
    }

}