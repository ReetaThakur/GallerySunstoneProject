package com.reeta.imageandvideosunstone.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.reeta.imageandvideosunstone.R
import com.reeta.imageandvideosunstone.allInterface.ShowFullImage
import com.reeta.imageandvideosunstone.allInterface.VideoClickListner
import com.reeta.imageandvideosunstone.modelClass.VideoModel
import com.squareup.picasso.Picasso

/*
This adapter class will use for initilization purpose of our videos and wrap our data to respective video
view
 */
class VideoAdapter(val videoList: ArrayList<VideoModel>, var listner: VideoClickListner) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    /* onCreateViewHolder wil use for creating the view, this method will not call every time for creating
    view because in recycler view we will use those view that already swipped to screen , it will return
    viewHolder object
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_layout, parent, false)
        return VideoViewHolder(view)
    }

    /*
   onBindViewHolder will bind our data to that particular view and show on screen, if in our list have 100
   item then this method will call 100 times
    */
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        var videoModel: VideoModel = videoList[position]
        holder.thumbnail.setImageBitmap(videoModel.thumbnail)
        holder.itemView.setOnClickListener {
            listner.onVideoClick(position)
        }
    }

    /*
    getItemCount method will retrun total no. of item present inside the list, so our android know how many
    items is in the list, this is method who will call first when adapter will call.
     */
    override fun getItemCount(): Int {
        return videoList.size
    }

    /*
   VideoViewHolder will holder the all view references that present inside our layout file,
   onBindViewHolder and this VideoViewHolder will call same number of time
    */
    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnail: ImageView = itemView.findViewById(R.id.imThumbnail)

    }
}