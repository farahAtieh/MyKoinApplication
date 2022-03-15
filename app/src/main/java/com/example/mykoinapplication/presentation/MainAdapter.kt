package com.example.mykoinapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mykoinapplication.data.model.User
import com.example.mykoinapplication.databinding.RowDataItemBinding

class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val mBinding = RowDataItemBinding.inflate(LayoutInflater.from(parent.context))
        return DataViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    override fun getItemCount(): Int = users.size

    public class DataViewHolder(val mBinding: RowDataItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(user: User) {
            mBinding.user = user
            Glide.with(mBinding.imageViewAvatar.context)
                .load(user.avatar)
                .into(mBinding.imageViewAvatar)
        }
    }

    fun addData(list: List<User>){
        users.addAll(list)
    }
}