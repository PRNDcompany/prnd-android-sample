package kr.co.prnd.sample

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class HeyDealerTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    var drawableStartSize: Int = NO_DRAWABLE_SIZE
        set(value) {
            field = value.coerceAtLeast(NO_DRAWABLE_SIZE)
            if (field != NO_DRAWABLE_SIZE) {
                setCompoundDrawablesIfNeeded()
            }
        }

    var drawableEndSize: Int = NO_DRAWABLE_SIZE
        set(value) {
            field = value.coerceAtLeast(NO_DRAWABLE_SIZE)
            if (field != NO_DRAWABLE_SIZE) {
                setCompoundDrawablesIfNeeded()
            }
        }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeyDealerTextView)
        drawableStartSize = typedArray.getDimensionPixelSize(
            R.styleable.HeyDealerTextView_drawableStartSize,
            NO_DRAWABLE_SIZE
        )
        drawableEndSize = typedArray.getDimensionPixelSize(
            R.styleable.HeyDealerTextView_drawableEndSize,
            NO_DRAWABLE_SIZE
        )
        typedArray.recycle()

        setCompoundDrawablesIfNeeded()
    }

    private fun setCompoundDrawablesIfNeeded() {
        val drawables = compoundDrawablesRelative
        val hasDrawable = drawables.any { it != null }

        if (hasDrawable) {
            setCompoundDrawablesRelative(
                drawables[START_INDEX], // 0
                drawables[TOP_INDEX], // 1
                drawables[END_INDEX], // 2
                drawables[BOTTOM_INDEX], // 3
            )
        }
    }

    override fun setCompoundDrawables(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawables(
            left?.adjustDrawableSize(drawableStartSize),
            top,
            right?.adjustDrawableSize(drawableEndSize),
            bottom
        )
    }

    override fun setCompoundDrawablesRelative(
        start: Drawable?,
        top: Drawable?,
        end: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawablesRelative(
            start?.adjustDrawableSize(drawableStartSize),
            top,
            end?.adjustDrawableSize(drawableEndSize),
            bottom
        )
    }

    private fun Drawable.adjustDrawableSize(size: Int): Drawable = apply {
        if (size > NO_DRAWABLE_SIZE) {
            val bounds = Rect(bounds)
            bounds.right = bounds.left + size // width
            bounds.bottom = bounds.top + size // height
            this.bounds = bounds
        }
    }

    companion object {
        private const val NO_DRAWABLE_SIZE = -1
        private const val START_INDEX = 0
        private const val TOP_INDEX = 1
        private const val END_INDEX = 2
        private const val BOTTOM_INDEX = 3
    }
}