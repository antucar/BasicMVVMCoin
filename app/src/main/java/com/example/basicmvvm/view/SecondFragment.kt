package com.example.basicmvvm.view
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.basicmvvm.R
import com.example.basicmvvm.adapter.CoinAdapter

import viewmodel.SecondFragmentViewModel


class SecondFragment : Fragment() {

    private lateinit var viewModel: SecondFragmentViewModel
    private val coinadapter = CoinAdapter(arrayListOf())
    private lateinit var coinRecyclerView: RecyclerView
    private lateinit var errormessage: TextView
    private lateinit var progressbar: ProgressBar
    private lateinit var swiperefreh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinRecyclerView = view.findViewById(R.id.coinRecyclerView)
        errormessage = view.findViewById(R.id.errormessage)
        progressbar = view.findViewById(R.id.progress_bar)
        swiperefreh = view.findViewById(R.id.reflesh_lay)

        viewModel = ViewModelProvider(requireActivity()).get(SecondFragmentViewModel::class.java)
        viewModel.refreshData()

        coinRecyclerView.adapter = coinadapter
        coinRecyclerView.layoutManager = LinearLayoutManager(context)

        swiperefreh.setOnRefreshListener {
            coinRecyclerView.visibility = View.GONE
            errormessage.visibility = View.GONE
            viewModel.refreshData()
            swiperefreh.isRefreshing = false
        }

        observeData()
    }

    private fun observeData() {
        viewModel.coins.observe(viewLifecycleOwner, Observer {
            it?.let {
                coinRecyclerView.visibility = View.VISIBLE
                coinadapter.updateCoin(it)
            }
        })
        viewModel.coinerror.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    errormessage.visibility = View.VISIBLE
                } else {
                    errormessage.visibility = View.GONE
                }
            }
        })
        viewModel.coinprogress.observe(viewLifecycleOwner, Observer { progress ->
            progress?.let {
                if (it) {
                    progressbar.visibility = View.VISIBLE
                    errormessage.visibility = View.GONE
                    coinRecyclerView.visibility = View.GONE
                } else {
                    progressbar.visibility = View.GONE
                }
            }
        })
    }
}
