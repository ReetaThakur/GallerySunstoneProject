package com.reeta.imageandvideosunstone.allInterface

/*
This interface will use for showing our video when user will click any of the video
onVideoClick method will take that clicked video position as parameter, and This interface
will implement in the ShowVideoFragment
 */
interface VideoClickListner {

    fun onVideoClick(position: Int)
}