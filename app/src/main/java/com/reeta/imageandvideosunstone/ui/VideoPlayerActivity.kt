package com.reeta.imageandvideosunstone.ui

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import com.reeta.imageandvideosunstone.R
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.android.synthetic.main.custom_controller.*

/*
This screen will show our video by playing with backword, forword, and pay pause button
and with one video controller for controlling the video
 */
class VideoPlayerActivity : AppCompatActivity() {
    lateinit var videoName: String
    lateinit var videoPath: String
    var isOpen: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        videoName = intent.getStringExtra("videoName").toString()
        videoPath = intent.getStringExtra("videoPath").toString()

        playVideo.setVideoURI(Uri.parse(videoPath))

        /*
        when we click on the video will play or pause
         */
        playVideo.setOnPreparedListener {
            idSeelBarProgress.max = playVideo.duration
            playVideo.start()
        }

        playvideoName.text = videoName

        /*
        for back the video 10 second before
         */
        idIBBack.setOnClickListener {
            playVideo.seekTo(playVideo.currentPosition - 10000)

        }

        /*
        for forword the video 10 seconds after
         */
        idIBForword.setOnClickListener {
            playVideo.seekTo(playVideo.currentPosition + 10000)
        }

        /*
        button for pay and pause video, if video is playing the while clicking it will pasue
        and if video is pause then it will play and change button image accordingly
         */
        idIBPlay.setOnClickListener {
            if (playVideo.isPlaying) {
                playVideo.pause()
                idIBPlay.setImageDrawable(resources.getDrawable(R.drawable.play))
            } else {
                playVideo.start()
                idIBPlay.setImageDrawable(resources.getDrawable(R.drawable.ic_pause))
            }
        }

        /*
        for showing the screen while clicking the screen and hinding the screen if it already
        showing
         */
        idRLVideo.setOnClickListener {
            if (isOpen) {
                hideControls()
                isOpen = false
            } else {
                showControls()
                isOpen = true
            }
        }
        setHandler()
        initilizeSeekbar()
    }

    /*
    This method will show progress bar and the timing of the video
     */
    private fun setHandler() {
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            override fun run() {
                if (playVideo.duration > 0) {
                    val curPos = playVideo.currentPosition
                    idSeelBarProgress.progress = curPos
                    idTVTime.setText("" + convertTime(playVideo.duration - curPos))
                }
                handler.postDelayed(this, 0)
            }
        }
        handler.postDelayed(runnable, 500)
    }

    /*
    This method will give timing of that video in partivular format for showing the video
    timing
     */
    private fun convertTime(ms: Int): String? {
        val time: String
        val seconds: Int
        val minutes: Int
        val hours: Int
        var x: Int = ms / 1000
        seconds = x % 60
        x /= 60
        minutes = x % 60
        x /= 60
        hours = x % 24
        time = if (hours != 0) {
            String.format("%02d", hours) + ":" + String.format(
                "%02d",
                minutes
            ) + ":" + String.format("%02d", seconds)
        } else {
            String.format("%02d", minutes) + ":" + String.format("%02d", seconds)
        }
        return time
    }

    /*
    This method will show seekbar to showing that how much video is remaing or controlling
    that video as user want
     */
    private fun initilizeSeekbar() {
        idSeelBarProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (idSeelBarProgress.id == R.id.idSeelBarProgress) {
                    if (fromUser) {
                        playVideo.seekTo(progress)
                        playVideo.start()
                        var curPos: Int = playVideo.currentPosition
                        idTVTime.text = convertTime(playVideo.duration - curPos)
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    /*
    If clicking on the screen our screen will hide, all these functionality will controll
    by this method
     */
    fun hideControls() {
        idRLControll.visibility = View.GONE
        val window: Window = this.window
        if (window == null) {
            return
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        var decordView: View = window.decorView
        if (decordView != null) {
            var uiOption: Int = decordView.systemUiVisibility
            if (Build.VERSION.SDK_INT >= 14) {
                uiOption = View.SYSTEM_UI_FLAG_LOW_PROFILE
            }
            if (Build.VERSION.SDK_INT >= 16) {
                uiOption = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
            if (Build.VERSION.SDK_INT >= 19) {
                uiOption = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            }
            decordView.systemUiVisibility = uiOption
        }
    }

    /*
    If clicking on the screen our screen will show again, all these functionality will
    control by this method
     */
    fun showControls() {
        idRLControll.visibility = View.VISIBLE
        val window: Window = this.window
        if (window == null) {
            return
        }
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
        var decordView: View = window.decorView
        if (decordView != null) {
            var uiOption: Int = decordView.systemUiVisibility
            if (Build.VERSION.SDK_INT >= 14) {
                uiOption = View.SYSTEM_UI_FLAG_LOW_PROFILE
            }
            if (Build.VERSION.SDK_INT >= 16) {
                uiOption = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            }
            if (Build.VERSION.SDK_INT >= 19) {
                uiOption = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            }
            decordView.systemUiVisibility = uiOption
        }
    }
}