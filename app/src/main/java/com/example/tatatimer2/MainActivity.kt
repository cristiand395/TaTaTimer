package com.example.tatatimer2

import android.media.MediaParser
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var counterActive = false;
    lateinit var countDownTimer :CountDownTimer;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar.max = 600
        seekBar.progress = 30
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                timerSet(i)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Do something
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Do something
            }
        })
    }

    fun timerSet(timeLeft: Int){
        val minutes = timeLeft / 60
        val seconds = timeLeft - (minutes * 60)
        var secondsString = seconds.toString()
        if (seconds.equals(0)) {
            secondsString = "00"
        }
        else if (seconds < 10){
            secondsString = "0${secondsString}"
        }
        timerTextView.text = "${minutes} : ${secondsString}"
        Log.i("seconds", "${timeLeft}")
    }

    fun Start(view: View){
        if (counterActive){
            counterActive = false
            timerTextView.text = "0 : 30"
            seekBar.progress = 30
            seekBar.isEnabled = true
            buttonText.text = "Start"
            countDownTimer.cancel()

        } else {
            counterActive = true
            seekBar.isEnabled = false
            buttonText.text = "Reset"

            countDownTimer = object : CountDownTimer(((seekBar.progress) * 1000).toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timerSet((millisUntilFinished / 1000).toInt())
                }

                override fun onFinish() {
                    val mediaPlayer: MediaPlayer = MediaPlayer.create(applicationContext,R.raw.sound)
                    mediaPlayer.start()

                }
            }.start()

        }


    }
}


