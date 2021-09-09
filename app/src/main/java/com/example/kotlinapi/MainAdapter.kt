package com.example.kotlinapi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


//import kotlinx.android.synthetic.main.item_character.view.*

class MainAdapter(val results: ArrayList<MainModel.Result>, val listener: OnAdapterListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.bind(result)
        Log.d("MainAdapter", "result.image: ${result.image}")
//        Glide.with(holder.view)
//            .load(result.image)
//            .centerCrop()
//            .placeholder(R.drawable.img_placeholder)
//            .error((R.drawable.img_placeholder))
//                //ketika pake plugins android extensions
//            .into(holder.view.imageView)
//
//        holder.view.setOnClickListener {
//            listener.onClick(result)
//        }
    }

    override fun getItemCount() = results.size

//    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
//        val tv = view.findViewById<TextView>(R.id.textView)
//        //val tv2 = view.findViewById<TextView>(R.id.imageView)
//
//        fun bind(result: MainModel.Result) {
//            tv.text = result.title
//            //tv2.text = result.image
//        }
//    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tv = view.findViewById<TextView>(R.id.textView)
        //val tv2 = view.findViewById<TextView>(R.id.imageView)

        fun bind(result: MainModel.Result) {
            tv.text = result.title
            //tv2.text = result.image

            Glide.with(view.context)
                .load(result.image)
                .centerCrop()
                .placeholder(R.drawable.img_placeholder)
                .error((R.drawable.img_placeholder))
                //ketika tidak pake android extensions
                .into(view.findViewById(R.id.imageView))

            view.setOnClickListener {
                listener.onClick(result)
            }

        }
    }

    fun setData(data: List<MainModel.Result>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(result: MainModel.Result)
    }
}