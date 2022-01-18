package com.reeta.imageandvideosunstone.fragments

import android.app.Activity
import android.app.Dialog
import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.reeta.imageandvideosunstone.R
import com.reeta.imageandvideosunstone.adapter.ImageAdapter
import com.reeta.imageandvideosunstone.allInterface.ShowFullImage
import kotlinx.android.synthetic.main.fragment_show_images.*
import java.util.ArrayList


class ShowImagesFragment : Fragment(R.layout.fragment_show_images),ShowFullImage {

    var imageUri = ArrayList<Uri>()
    lateinit var imageAdapter: ImageAdapter
    lateinit var dialog: Dialog
    private val REQUEST_CODE = 1
    private val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
     //   ActivityCompat.requestPermissions(context as Activity, permission, REQUEST_CODE)
        setContentProvider()
        dialog= context?.let { Dialog(it) }!!
    }

    private fun setContentProvider() {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.DATE_MODIFIED
        )
        val selection: String? = null
        val selectionargs: Array<String>? = null
        val orderby: String? = null
        val content_uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val resolver = requireActivity().contentResolver
        val cursor = resolver.query(content_uri, projection, selection, selectionargs, orderby)
        if (cursor != null) {
            cursor.moveToPosition(0)
            while (true) {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val image_uri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                imageUri.add(image_uri)
                if (!cursor.isLast) cursor.moveToNext() else break
            }
            setRecyclerView()
        }
    }

    private fun setRecyclerView() {
        imageAdapter = ImageAdapter(imageUri,this)
        val gridLayoutManager = GridLayoutManager(context, 2)
        imageRecyclerView.apply {
            layoutManager = gridLayoutManager
            adapter = imageAdapter
        }
    }

    override fun showFullImage(uri: Uri) {
       setDialogBox(uri)
    }

    fun setDialogBox(uri: Uri) {
        dialog?.setContentView(R.layout.dialog_box)
        var image:ImageView=dialog!!.findViewById(R.id.dialogImage)
        var close:Button=dialog!!.findViewById(R.id.btnClose)
        dialog.show()
        Glide.with(image).load(uri).into(image)
        close.setOnClickListener {
            dialog.dismiss()
        }
    }


}