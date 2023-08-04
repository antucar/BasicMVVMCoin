package com.example.basicmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.basicmvvm.R
import com.example.basicmvvm.viewmodel.AskAIViewModel


class AskAIFragment : Fragment() {

    private lateinit var viewModel: AskAIViewModel

    private lateinit var response: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask_a_i, container, false)




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(AskAIViewModel::class.java)


        val messageEditText = view.findViewById<EditText>(R.id.messageai).toString()
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            viewModel.giveCoin(messageEditText)
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.messageinmodel.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            response = it.chatbotResponse.toString()
        }
            )
    }


}