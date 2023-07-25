package com.example.basicmvvm.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.basicmvvm.R
import com.example.basicmvvm.databinding.CoinInfoBinding
import com.example.basicmvvm.model.CoinModel
import com.example.basicmvvm.view.SecondFragmentDirections


class CoinAdapter(val coinlist: ArrayList<CoinModel>): RecyclerView.Adapter<CoinAdapter.CoinViewHolder>(),CoinClickListener {

    class CoinViewHolder(var view: CoinInfoBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CoinInfoBinding>(inflater,R.layout.coin_info,parent,false)
//        val view = inflater.inflate(R.layout.coin_info,parent,false)
        return CoinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coinlist.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
    holder.view.coin = coinlist[position]
        holder.view.listener = this
    }

    fun updateCoin(newCoinList: List<CoinModel>){
        coinlist.clear()
        coinlist.addAll(newCoinList)
        notifyDataSetChanged()
    }

    override fun onCoinClicked(v: View) {
        val uuid = v.findViewById<TextView>(R.id.coinuuid).text.toString().toInt()

        // Provide the "uuid" argument when calling the action
        val action = SecondFragmentDirections.actionSecondFragmentToCoinDetailFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }

}