package com.example.luis.mycvapp.profile

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.luis.mycvapp.R
import com.example.luis.mycvapp.extension.inflate
import com.example.luis.mycvapp.model.ItemProfile
import kotlinx.android.synthetic.main.item_menu_profile.view.*

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){


    private val list = mutableListOf<ItemProfile>()
    private var callback: OnSelectedCallback? = null


    fun updateList(updates: List<ItemProfile>?) {
        list.clear()
        updates?.let {
            list.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun setCallback(callback: OnSelectedCallback) {
        this.callback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(parent.inflate(R.layout.item_menu_profile))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bindItem(list[position])
    }


    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun  bindItem(itemProfile : ItemProfile) = with(itemView){
           itemProfile.let {item ->
               title.text = context.getString(item.resLabel)
               icon.setImageDrawable(context.getDrawable(item.iconId))
               setOnClickListener { callback?.onItemSelected(item) }
           }

       }

    }

    interface OnSelectedCallback{
        fun onItemSelected(item: ItemProfile)
    }

}







