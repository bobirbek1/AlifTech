package com.idrok.aliftech.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idrok.aliftech.Model
import com.idrok.aliftech.databinding.MainRvItemBinding

class MainAdapter(private val itemclickListener: ((Model) -> Unit)) :
    RecyclerView.Adapter<MainAdapter.VH>() {

    private var list = arrayListOf<Model>()

    fun setData(list: ArrayList<Model>) {
        this.list = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainRvItemBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemclickListener.invoke(list[position])
        }
        holder.onBind(list[position])
    }

    override fun getItemCount() = list.size


    class VH(private val binding: MainRvItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: Model) {
            binding.model = model
        }

    }

}