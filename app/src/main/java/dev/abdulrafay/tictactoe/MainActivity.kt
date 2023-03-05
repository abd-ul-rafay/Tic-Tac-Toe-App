package dev.abdulrafay.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import dev.abdulrafay.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var xo = 'o'
    private var ch: CharArray = CharArray(9) {'c'}
    private var incrementForDraw = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAgain.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
            Animatoo.animateShrink(this)
        }

        binding.gridLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation))

        binding.imageButton1.setOnClickListener(this)
        binding.imageButton2.setOnClickListener(this)
        binding.imageButton3.setOnClickListener(this)
        binding.imageButton4.setOnClickListener(this)
        binding.imageButton5.setOnClickListener(this)
        binding.imageButton6.setOnClickListener(this)
        binding.imageButton7.setOnClickListener(this)
        binding.imageButton8.setOnClickListener(this)
        binding.imageButton9.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        setButtonImg(p0)

        if (p0 != null) {
            when (p0.id) {
                binding.imageButton1.id -> {
                    ch[0] = xo
                }
                binding.imageButton2.id -> {
                    ch[1] = xo
                }
                binding.imageButton3.id -> {
                    ch[2] = xo
                }
                binding.imageButton4.id -> {
                    ch[3] = xo
                }
                binding.imageButton5.id -> {
                    ch[4] = xo
                }
                binding.imageButton6.id -> {
                    ch[5] = xo
                }
                binding.imageButton7.id -> {
                    ch[6] = xo
                }
                binding.imageButton8.id -> {
                    ch[7] = xo
                }
                binding.imageButton9.id -> {
                    ch[8] = xo
                }
            }
        }

        check()
    }

    private fun setButtonImg(p0: View?) {

        incrementForDraw++
        if (incrementForDraw > 9) {
            binding.textView.text = "Match Drawn"
            binding.textView.textSize = 35F
            disableAll()
        }

        if (xo == 'o') {
            (p0 as ImageButton).setImageResource(R.drawable.o)
            xo = 'x'
            if (incrementForDraw <= 9)
                binding.textView.text = "Player X turn"
        } else if (xo == 'x') {
            (p0 as ImageButton).setImageResource(R.drawable.x)
            xo = 'o'
            if (incrementForDraw <= 9)
                binding.textView.text = "Player O turn"
        }

        (p0 as ImageButton).isEnabled = false

    }

    private fun check() {
        if ((ch[0] == ch[1] && ch[1] == ch[2] && ch[0] != 'c') || (ch[0] == ch[3] && ch[3] == ch[6] && ch[0] != 'c')
         || (ch[0] == ch[4] && ch[4] == ch[8] && ch[0] != 'c') || (ch[1] == ch[4] && ch[4] == ch[7] && ch[1] != 'c')
         || (ch[2] == ch[5] && ch[5] == ch[8] && ch[2] != 'c') || (ch[2] == ch[4] && ch[4] == ch[6] && ch[2] != 'c')
         || (ch[3] == ch[4] && ch[4] == ch[5] && ch[3] != 'c') || (ch[6] == ch[7] && ch[7] == ch[8] && ch[6] != 'c')) {

            disableAll()

            if (ch[0] == ch[1] && ch[1] == ch[2] && ch[0] != 'c') {
                showResult(binding.imageButton1, binding.imageButton2, binding.imageButton3)
            } else if (ch[0] == ch[3] && ch[3] == ch[6] && ch[0] != 'c') {
                showResult(binding.imageButton1, binding.imageButton4, binding.imageButton7)
            } else if (ch[0] == ch[4] && ch[4] == ch[8] && ch[0] != 'c') {
                showResult(binding.imageButton1, binding.imageButton5, binding.imageButton9)
            } else if (ch[1] == ch[4] && ch[4] == ch[7] && ch[1] != 'c') {
                showResult(binding.imageButton2, binding.imageButton5, binding.imageButton8)
            } else if (ch[2] == ch[5] && ch[5] == ch[8] && ch[2] != 'c') {
                showResult(binding.imageButton3, binding.imageButton6, binding.imageButton9)
            } else if (ch[2] == ch[4] && ch[4] == ch[6] && ch[2] != 'c') {
                showResult(binding.imageButton3, binding.imageButton5, binding.imageButton7)
            } else if (ch[3] == ch[4] && ch[4] == ch[5] && ch[3] != 'c') {
                showResult(binding.imageButton4, binding.imageButton5, binding.imageButton6)
            } else if (ch[6] == ch[7] && ch[7] == ch[8] && ch[6] != 'c') {
                showResult(binding.imageButton7, binding.imageButton8, binding.imageButton9)
            }

            if (xo == 'o') {
                binding.textView.text = "Player X win"
                binding.textView.textSize = 35F
            } else if (xo == 'x') {
                binding.textView.text = "Player O win"
                binding.textView.textSize = 35F
            }
        }
    }

    private fun showResult(imageButton1: ImageButton, imageButton2: ImageButton, imageButton3: ImageButton) {
//        imageButton1.setBackgroundColor(Color.RED)
//        imageButton2.setBackgroundColor(Color.RED)
//        imageButton3.setBackgroundColor(Color.RED)

        val thread = object : Thread() {
            override fun run() {
                super.run()

                for (i in 0..5) {
                    imageButton1.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, android.R.anim.fade_in))
                    imageButton2.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, android.R.anim.fade_in))
                    imageButton3.startAnimation(AnimationUtils.loadAnimation(this@MainActivity, android.R.anim.fade_in))
                    sleep(200)
                }

            }
        }.start()

    }

    private fun disableAll() {
        binding.imageButton1.isEnabled = false
        binding.imageButton2.isEnabled = false
        binding.imageButton3.isEnabled = false
        binding.imageButton4.isEnabled = false
        binding.imageButton5.isEnabled = false
        binding.imageButton6.isEnabled = false
        binding.imageButton7.isEnabled = false
        binding.imageButton8.isEnabled = false
        binding.imageButton9.isEnabled = false
    }
}
