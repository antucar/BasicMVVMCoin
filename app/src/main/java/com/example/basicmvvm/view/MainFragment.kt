package com.example.basicmvvm.view

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.basicmvvm.R


class MainFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.button1)
        val aibutton = view.findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_secondFragment)
        }
        aibutton.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_askAIFragment)
        }

    }
}