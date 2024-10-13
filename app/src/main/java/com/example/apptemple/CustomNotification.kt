package com.example.apptemple

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import androidx.core.content.ContextCompat

class CustomNotification(private val context: Context) {
    private var dialog: Dialog? = null

    // Метод для показа плашки с текстом
    fun showNotification(message: String) {
        // Проверяем, если плашка уже отображается, чтобы не создавать дубликат
        if (dialog != null && dialog!!.isShowing) {
            updateMessage(message) // Обновляем текст, если плашка уже есть
            return
        }

        dialog = Dialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_notification_panel, null)

        // Найти TextView и установить текст
        val textView = view.findViewById<TextView>(R.id.notificationText)
        textView.text = message

        dialog!!.setContentView(view)

        // Убираем затемнение экрана позади
        dialog!!.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)

        // Устанавливаем прозрачный фон
        dialog!!.window?.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.color.transparent))

        // Позиционируем диалог в верхней части экрана
        val params: WindowManager.LayoutParams? = dialog!!.window?.attributes
        params?.gravity = Gravity.TOP
        params?.y = 50 // Отступ сверху
        dialog!!.window?.attributes = params

        dialog!!.show()
    }

    // Метод для скрытия плашки
    fun hideNotification() {
        dialog?.dismiss()
        dialog = null
    }

    // Метод для обновления текста в уже показанной плашке
    private fun updateMessage(message: String) {
        val textView = dialog?.findViewById<TextView>(R.id.notificationText)
        textView?.text = message
    }
}