package com.dhakadigital.enterprisepaymentapicall_kotlin.adaptetrs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dhakadigital.enterprisepaymentapicall_kotlin.R
import com.dhakadigital.enterprisepaymentapicall_kotlin.models.response.Payload
import de.hdodenhof.circleimageview.CircleImageView

class EnterpriseListAdapters(private val data: List<Payload>) :
    RecyclerView.Adapter<EnterpriseListAdapters.ModelViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EnterpriseListAdapters.ModelViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(
            R.layout.dummy_list_layout,
            parent, false
        )
        return ModelViewHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: EnterpriseListAdapters.ModelViewHolder, position: Int) {
        val currentItem = data[position]
        Glide.with(holder.itemView).load(currentItem.logo).into(holder.logo)
        holder.titleTV.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var logo: CircleImageView
        var titleTV: TextView


        init {
            titleTV = itemView.findViewById(R.id.enterpriseNameId)
            logo = itemView.findViewById(R.id.enterproseLogoId)

        }

    }

}