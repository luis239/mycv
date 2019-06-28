package com.example.luis.mycvapp.education

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.luis.domain.education.model.EducationModel
import com.example.luis.mycvapp.R
import com.example.luis.mycvapp.extension.inflate
import kotlinx.android.synthetic.main.item_education_card.view.*

class EducationAdapter (
    private val list: ArrayList<EducationModel>
): RecyclerView.Adapter<EducationAdapter.ProfileViewHolder>(){



    private var callback: OnSelectedCallback? = null
    private var callbackAdd: OnAddCallBack? = null


    fun updateList(updates: List<EducationModel>?) {
        /*list.clear()
        updates?.let {
            list.addAll(it)
        }*/
        notifyDataSetChanged()
    }

    fun setCallback(callback: OnSelectedCallback) {
        this.callback = callback
    }

    fun setAddCallback(callback: OnAddCallBack) {
        this.callbackAdd = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(parent.inflate(R.layout.item_education_card))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bindItem(list[position])
    }


    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun  bindItem(educationModel: EducationModel) = with(itemView){
            educationModel.let {item ->
                degree_et.setText(item.degree)
                degree_et.addTextChangedListener(object : TextWatcher{
                    override fun afterTextChanged(e: Editable?) {
                        item.degree =  e.toString()
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                })


                university_et.setText(item.university)
                university_et.addTextChangedListener(object : TextWatcher{
                    override fun afterTextChanged(e: Editable?) {
                        item.university =  e.toString()
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                })
                year_et.setText(item.year)
                year_et.addTextChangedListener(object : TextWatcher{
                    override fun afterTextChanged(e: Editable?) {
                        item.year =  e.toString()
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                })
                addNew.setOnClickListener { list.add(EducationModel())
                notifyDataSetChanged()}
                deleteAction.setOnClickListener { callback?.onItemSelected(adapterPosition) }
           }

       }

    }

    interface OnSelectedCallback{
        fun onItemSelected(position: Int)
    }
    interface OnAddCallBack{
        fun onAddItem()
    }

}







