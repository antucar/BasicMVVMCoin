package com.example.basicmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.basicmvvm.R
import com.example.basicmvvm.databinding.FragmentCoinDetailBinding
import com.example.basicmvvm.model.CoinModel
import com.example.basicmvvm.viewmodel.CoinDetailViewModel


class CoinDetailFragment : Fragment() {

    private var coinuuid = 0
    private lateinit var viewModel: CoinDetailViewModel
    private lateinit var binding : FragmentCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_coin_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            coinuuid = SecondFragmentArgs.fromBundle(it).uuid
        }
        viewModel = ViewModelProvider(requireActivity()).get(CoinDetailViewModel::class.java)
        viewModel.getDataFromRoom(coinuuid)


        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.selectedCoin.observe(viewLifecycleOwner, Observer { coinList ->
            coinList?.let {
                binding.coin = it
            }
        })
    }


}