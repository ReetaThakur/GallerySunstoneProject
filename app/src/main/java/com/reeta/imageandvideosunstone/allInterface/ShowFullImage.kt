package com.reeta.imageandvideosunstone.allInterface

import android.net.Uri

/*
This interface will use for showing our Full image when user will click any of the image
showFullImage method will take that clicked image uri as parameter, and This interface will
implement in the ShowImagesFragment
 */
interface ShowFullImage {

    fun showFullImage(uri: Uri)
}