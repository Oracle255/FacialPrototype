package com.example.afbstoryapp.view.customUI

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.capstone.prettyU.R

class EdtFormPassword @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs), View.OnTouchListener {

    private var toggleButtonImage: Drawable
    private var staticIcon: Drawable
    private val passwordHide = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    private val passwordShow =
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
    private var setInput = passwordHide
    private var isPasswordVisible = false

    private val textPaint = TextPaint().apply {
        color = resources.getColor(R.color.btnDisable)
        textSize = 22f
        isAntiAlias = true
    }

    private val backgroundPaintPrimary = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.FILL
        alpha = 25
    }

    private val backgroundPaintSecondary = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
        alpha = 45
    }

    private val passwordErrorText = context.getString(R.string.edtHintPasswordMinimum)

    init {
        toggleButtonImage =
            ContextCompat.getDrawable(context, R.drawable.ic_pass_on) as Drawable
        staticIcon = ContextCompat.getDrawable(context, R.drawable.ic_pass) as Drawable
        setOnTouchListener(this)
        setButtonDrawables(startOfTheText = staticIcon, endOfTheText = toggleButtonImage)

        inputType = setInput

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                staticIcon = ContextCompat.getDrawable(context, R.drawable.ic_pass) as Drawable
                setButtonDrawables(startOfTheText = staticIcon, endOfTheText = toggleButtonImage)
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //hint = context.getString(R.string.hint_input_password)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        if (text!!.length in 1..7) {
            drawCanvas(canvas)
        }
    }

    private fun drawCanvas(canvas: Canvas) {
        val width = width - paddingLeft - paddingRight
        val layout = StaticLayout.Builder.obtain(
            passwordErrorText,
            0,
            passwordErrorText.length,
            textPaint,
            width
        )
            .setAlignment(Layout.Alignment.ALIGN_CENTER)
            .setLineSpacing(1.0f, 1.0f)
            .setIncludePad(false)
            .build()

        val backgroundHeight = layout.height + 5f
        val backgroundTop = height - backgroundHeight

        canvas.drawRect(
            paddingLeft.toFloat(),
            backgroundTop,
            (width + paddingLeft).toFloat(),
            (height - backgroundHeight / 2),
            backgroundPaintPrimary
        )

        canvas.drawRect(
            paddingLeft.toFloat(),
            (height - backgroundHeight / 2),
            (width + paddingLeft).toFloat(),
            height.toFloat(),
            backgroundPaintSecondary
        )

        canvas.save()
        canvas.translate(paddingLeft.toFloat(), backgroundTop)
        layout.draw(canvas)
        canvas.restore()
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val toggleButtonStart: Float
            val toggleButtonEnd: Float
            var isToggleButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                toggleButtonEnd = (toggleButtonImage.intrinsicWidth + paddingStart).toFloat()
                if (event.x < toggleButtonEnd) isToggleButtonClicked = true
            } else {
                toggleButtonStart =
                    (width - paddingEnd - toggleButtonImage.intrinsicWidth).toFloat()
                if (event.x > toggleButtonStart) isToggleButtonClicked = true
            }

            if (isToggleButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        toggleButtonImage = ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_pass_on
                        ) as Drawable
                        return true
                    }

                    MotionEvent.ACTION_UP -> {
                        toggleIcon()
                        return true
                    }

                    else -> return false
                }
            } else return false
        }
        return false
    }

    private fun toggleIcon() {
        isPasswordVisible = !isPasswordVisible
        inputType = if (isPasswordVisible) passwordShow else passwordHide
        setSelection(text?.length ?: 0)
        toggleButtonImage = ContextCompat.getDrawable(
            context,
            if (isPasswordVisible) R.drawable.ic_pass_off else R.drawable.ic_pass_on
        ) as Drawable
        setButtonDrawables(startOfTheText = staticIcon, endOfTheText = toggleButtonImage)
    }
}
