package com.ricknout.rugbyranker.ui.common

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class OnBackgroundClickItemTouchListener(context: Context, onBackgroundClick: () -> Unit) : RecyclerView.OnItemTouchListener {

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            onBackgroundClick()
            return super.onSingleTapUp(e)
        }
    }

    private val gestureDetector = GestureDetectorCompat(context, gestureListener)

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        if (rv.findChildViewUnder(e.x, e.y) == null) {
            gestureDetector.onTouchEvent(e)
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }
}