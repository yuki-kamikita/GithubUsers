package com.example.githubusers.core

import android.util.Log

/**
 * fun名とclass名、行数付きのログ出力
 */
class Logger {
    companion object {
        fun error(exception: Exception) {
            val ste = Thread.currentThread().stackTrace[3]
            val fileName = ste.fileName
            val methodName = ste.methodName
            val line = ste.lineNumber
            Log.e("$methodName($fileName:$line)", "$exception")
        }

        fun error(string: String) {
            val ste = Thread.currentThread().stackTrace[3]
            val fileName = ste.fileName
            val methodName = ste.methodName
            val line = ste.lineNumber
            Log.e("$methodName($fileName:$line)", string)
        }

        fun debug(string: String) {
            val ste = Thread.currentThread().stackTrace[3]
            val fileName = ste.fileName
            val methodName = ste.methodName
            val line = ste.lineNumber
            Log.d("$methodName($fileName:$line)", string)
        }
    }
}