package kr.co.prnd.sample

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.prnd.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.slider.addOnChangeListener { _, value, _ ->
            binding.textView.drawableStartSize = value.dp.toInt()
        }
    }

    private inline val Float.dp: Float
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
}