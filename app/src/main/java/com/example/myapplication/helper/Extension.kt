package com.example.myapplication.helper

import android.content.Context
import android.widget.Toast
var toast: Toast? = null



fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    if (toast != null) {
        toast!!.cancel()
    }
    toast = Toast.makeText(context, this.toString(), duration).apply { show() }
    return toast!!
}