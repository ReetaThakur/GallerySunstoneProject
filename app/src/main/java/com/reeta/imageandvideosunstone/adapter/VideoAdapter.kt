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

class VideoAdapter(val videoList:ArrayList<VideoModel>,var listner: VideoClickListner):RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.video_layout,parent,false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        var videoModel:VideoModel=videoList[position]
        holder.thumbnail.setImageBitmap(videoModel.thumbnail)
        holder.itemView.setOnClickListener {
            listner.onVideoClick(position)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    class VideoViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var thumbnail:ImageView=itemView.findViewById(R.id.imThumbnail)

    }
}