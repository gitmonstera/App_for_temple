package com.example.apptemple.domain

import android.content.Context
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.example.apptemple.displayMessage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun checkUserData(
    login : String, password : String, context : Context
): Boolean = when {
    login.isBlank() && (password.isBlank() || password.length < 6) -> {
        context.displayMessage("Неправильный логин и пароль")
        false
    }
    login.isBlank() -> {
        context.displayMessage("Некорректный логин")
        false
    }
    password.isBlank() || password.length < 6 -> {
        context.displayMessage("Пароль слишком короткий. Минимальная длина - 6 символов")
        false
    }
    else -> true
}

fun checkEmail(
    email : String, context : Context
) : Boolean = when {
    email.isBlank() || !email.contains("@") -> {
        context.displayMessage("Некорректная почта. Отсутствует @")
        false
    }
    else -> true
}
//проверка даты на валидность будет работать, начиная с андроид 8
@RequiresApi(VERSION_CODES.O)
fun checkBirthdate(birthdate : String, context : Context): String? {
    return try {
        if (Regex("""^\d{2}\.\d{2}\.\d{4}$""").matches(birthdate)) {
            val date = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            when {
                date < LocalDate.now() -> birthdate
                else -> {
                    context.displayMessage("Некорректная дата рождения или неправильный формат даты (дд.мм.гггг)")
                    null
                }
            }
        } else null
    } catch (e: DateTimeParseException) {
        context.displayMessage("Некорректная дата рождения или неправильный формат даты (дд.мм.гггг)")
        null
    }
}