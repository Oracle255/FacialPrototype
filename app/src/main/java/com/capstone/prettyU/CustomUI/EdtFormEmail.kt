package com.example.afbstoryapp.view.customUI

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Editable
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

class EdtFormEmail @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs), View.OnTouchListener {

    private var clearButtonImage: Drawable
    private var staticIcon: Drawable

    init {
        clearButtonImage =
            ContextCompat.getDrawable(context, R.drawable.ic_text_clear) as Drawable
        staticIcon = ContextCompat.getDrawable(context, R.drawable.ic_email) as Drawable
        setOnTouchListener(this)
        setButtonDrawables(startOfTheText = staticIcon)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) showClearButton() else hideClearButton()

            }

            override fun afterTextChanged(s: Editable) {
            }
        })
    }

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

    private val emailErrorText = context.getString(R.string.text_msg_invalid_email)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = context.getString(R.string.hint_input_email)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START

        // TODO: TAMBAH fungsi regeX check format email
        if (text!!.length > 0) {
            drawCanvas(canvas)
        }
    }

    private fun drawCanvas(canvas: Canvas) {
        val width = width - paddingLeft - paddingRight
        val layout =
            StaticLayout.Builder.obtain(emailErrorText, 0, emailErrorText.length, textPaint, width)
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

    private fun showClearButton() {
        setButtonDrawables(startOfTheText = staticIcon, endOfTheText = clearButtonImage)
    }

    private fun hideClearButton() {
        setButtonDrawables(startOfTheText = staticIcon)
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
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                clearButtonEnd = (clearButtonImage.intrinsicWidth + paddingStart).toFloat()
                when {
                    event.x < clearButtonEnd -> isClearButtonClicked = true
                }
            } else {
                clearButtonStart = (width - paddingEnd - clearButtonImage.intrinsicWidth).toFloat()
                when {
                    event.x > clearButtonStart -> isClearButtonClicked = true
                }
            }
            if (isClearButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        clearButtonImage = ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_text_clear
                        ) as Drawable
                        showClearButton()
                        return true
                    }

                    MotionEvent.ACTION_UP -> {
                        clearButtonImage = ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_text_clear
                        ) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
                        return true
                    }

                    else -> return false
                }
            } else return false
        }
        return false
    }

}