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

/*
This adapter class will use for initilization of our images and wrap our data to respective views
 */
class ImageAdapter(var imageList: ArrayList<Uri>, private val showImage: ShowFullImage) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    /* onCreateViewHolder wil use for creating the view, this method will not call every time for creating
    view because in recycler view we will use those view that already swipped to screen , it will return
    viewHolder object
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_layout, parent, false)
        return ImageViewHolder(view)
    }

    /*
    onBindViewHolder will bind our data to that particular view and show on screen, if in our list have 20
    item then this method will call 20 times
     */
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Picasso.get().load(imageList[position])
            .resize(400, 400)
            .centerCrop()
            .into(holder.image)
        holder.image.setOnClickListener {
            showImage.showFullImage(imageList[position])
        }
    }

    /*
    getItemCount method will retrun total no. of item present inside the list, so our android know how many
    items is in the list, this is method who will call first when adapter will call.
     */
    override fun getItemCount(): Int {
        return imageList.size
    }

    /*
    ImageViewHolder will holder the all views references that present inside our layout file, onBindViewHolder
    and this ImageViewHolder will call same number of time
     */
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.imageView)
    }
}