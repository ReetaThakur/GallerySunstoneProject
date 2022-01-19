package com.reeta.imageandvideosunstone.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.reeta.imageandvideosunstone.R
import com.reeta.imageandvideosunstone.adapter.VideoAdapter
import com.reeta.imageandvideosunstone.allInterface.VideoClickListner
import com.reeta.imageandvideosunstone.modelClass.VideoModel
import com.reeta.imageandvideosunstone.ui.VideoPlayerActivity
import kotlinx.android.synthetic.main.fragment_show_videos.*
import java.util.ArrayList

/*
In this Fragment we are setting adapter and layoutManager for showing our device videos in
grid form and for clicking video we are implementing the VideoClickListner interface.
For fetching videos from device I am using content provider ,content provider will give
all videos and storing in the videoList list and give this list to adapter
 */
class ShowVideosFragment : Fragment(R.layout.fragment_show_videos), VideoClickListner {

    lateinit var videoAdapter: VideoAdapter
    var videoList = ArrayList<VideoModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getVideos()
    }

    /*
    getVideos method will fetch all videos from the device and store in the videoList
    list, I am fetching video image, path(means video), and video title. And make cursor
    object so cursor will give data one by one.
     */
    @SuppressLint("Range")
    fun getVideos() {
        val contentResolver: ContentResolver = requireActivity().contentResolver
        var uri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        var cursor = contentResolver.query(uri, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var videoTitle =
                    cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE))
                var videoPath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA))
                var videoThumbnail = ThumbnailUtils.createVideoThumbnail(
                    videoPath,
                    MediaStore.Images.Thumbnails.MINI_KIND
                )
                videoThumbnail?.let { VideoModel(videoTitle, videoPath, it) }
                    ?.let { videoList.add(it) }
            } while (cursor.moveToNext())
        }
        videoAdapter.notifyDataSetChanged()
    }

    /*
    I am setting recyclerView with adapter and layoutManager for showing my videos in grid
    form means two dimension form
     */
    fun setRecyclerView() {
        videoAdapter = VideoAdapter(videoList, this)
        val gridLayoutManager = GridLayoutManager(context, 2)
        videoRecyclerView.apply {
            adapter = videoAdapter
            layoutManager = gridLayoutManager
        }
    }

    /*
   this is interface override method if in case user want to play the video then video will
   play in another screen.
    */
    override fun onVideoClick(position: Int) {
        val intent = Intent(context, VideoPlayerActivity::class.java)
        intent.putExtra("videoName", videoList[position].videoName)
        intent.putExtra("videoPath", videoList[position].videoPath)
        startActivity(intent)
    }

}