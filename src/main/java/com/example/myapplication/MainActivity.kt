package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score = 0
    var imageArray = ArrayList <ImageView>()
    var handler : Handler = Handler()
    var runnable : Runnable = Runnable {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        score = 0
        imageArray = arrayListOf(imageview, imageview2,
            imageview3, imageview4,imageview5,imageview6,imageview7,
                    imageview8,imageview9)
        hidelmages()

        object : CountDownTimer(10000, 1000) {
            override fun onFinish(){
                timeText.text = "Time over"
                handler.removeCallbacks(runnable)
                for (image in imageArray)
                    image.visibility = View.INVISIBLE
            }

            override fun onTick(p0: Long) {
                timeText.text = "Time:" + p0 / 1000
            }

        }.start()
    }
    fun hidelmages(){
        runnable = object : Runnable{
            override fun run () {
                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                val random = Random()
                val index = random.nextInt(8 - 0)
                imageArray[index].visibility = View.VISIBLE

                handler.postDelayed(runnable, 500)
            }
        }
        handler.post(runnable)
    }
    fun increaseScore(view:View){
        score++

        scoreText.text = "Score:" + score
    }
}