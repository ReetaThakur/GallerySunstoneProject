package com.reeta.imageandvideosunstone.adapter

import android.net.Uri
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reeta.imageandvideosunstone.R
import com.reeta.imageandvideosunstone.allInterface.ShowFullImage
import com.squareup.picasso.Picasso

class ImageAdapter(var imageList:ArrayList<Uri>, private val showImage: ShowFullImage):RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.image_layout,parent,false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
      Picasso.get().load(imageList[position])
          .resize(400,400)
          .centerCrop()
          .into(holder.image)
        holder.image.setOnClickListener {
            showImage.showFullImage(imageList[position])
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class ImageViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
       var image:ImageView=itemView.findViewById(R.id.imageView)
    }
}