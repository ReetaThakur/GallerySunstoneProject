package com.reeta.imageandvideosunstone.modelClass

import android.graphics.Bitmap

/*
This is a data model class for storing the video information ,when we will take video
information we will store inside this class object and pass this class list for adapter
to showing videos
 */
data class VideoModel(var videoName: String, var videoPath: String, var thumbnail: Bitmap)