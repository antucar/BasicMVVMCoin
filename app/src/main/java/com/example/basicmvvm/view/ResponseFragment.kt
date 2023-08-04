package com.example.basicmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.basicmvvm.R
import com.example.basicmvvm.databinding.FragmentResponseBinding
import com.example.basicmvvm.viewmodel.AskAIViewModel
import viewmodel.BaseViewModel


class ResponseFragment : Fragment() {
    private var responseid: Long = 0
    private lateinit var binding : FragmentResponseBinding
    private lateinit var viewModel: AskAIViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_response,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(AskAIViewModel::class.java)

    }
}