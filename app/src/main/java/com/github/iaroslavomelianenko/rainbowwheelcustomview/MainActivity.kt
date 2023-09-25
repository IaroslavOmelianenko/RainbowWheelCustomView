package com.github.iaroslavomelianenko.rainbowwheelcustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    private lateinit var rainbowWheelView: RainbowWheelView
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rainbowWheelView = findViewById(R.id.rainbowWheelView)
        resetButton = findViewById(R.id.resetButton)
        var wheelPosition = 0

        //Download picture
        fun downloadImage(URL: String, view: ImageView): String{
            Picasso.get()
                .load(URL)
                .placeholder(android.R.drawable.stat_notify_sync)
                .error(androidx.constraintlayout.widget.R.drawable.abc_btn_switch_to_on_mtrl_00012)
                .resize(300,300)
                .centerCrop()
                .into(view)
            return ""
        }

        rainbowWheelView.setOnClickListener{
            imageView.setImageResource(R.drawable.ic_cross)

            rainbowWheelView.startRotation()
            wheelPosition = rainbowWheelView.wheelPosition

            when {
                wheelPosition in 0..51 -> {
                    tvTextField.text = "RED: Text"
                }

                wheelPosition in 52..102 -> {
                    downloadImage("https://avatars.dzeninfra.ru/get-zen_doc/98165/pub_63170d5ba38ab347e23508fd_63171d320f92f9676537bbf8/scale_1200", imageView)
                    tvTextField.text = "ORANGE: Picture"
                }
                wheelPosition in 103..153 -> {
                    tvTextField.text = "YELLOW: Text"
                }
                wheelPosition in 154..204 -> {
                    downloadImage("https://avatars.dzeninfra.ru/get-zen_doc/98165/pub_63170d5ba38ab347e23508fd_63171d320f92f9676537bbf8/scale_1200", imageView)
                    tvTextField.text = "GREEN: Picture"
                }
                wheelPosition in 205..255 -> {
                    tvTextField.text = "LIGHTBLUE: Text"
                }
                wheelPosition in 256..306 -> {
                    downloadImage("https://avatars.dzeninfra.ru/get-zen_doc/98165/pub_63170d5ba38ab347e23508fd_63171d320f92f9676537bbf8/scale_1200", imageView)
                    tvTextField.text = "BLUE: Picture"
                }
                wheelPosition in 307..360 -> {
                    tvTextField.text = "PURPLE: Text"
                }
            }
        }

        resetButton.setOnClickListener {
            rainbowWheelView.reset()
            tvTextField.text = ""
        }
    }
}