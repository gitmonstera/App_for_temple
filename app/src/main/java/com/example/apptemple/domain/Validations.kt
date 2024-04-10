package com.example.apptemple.domain

import android.content.Context
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import com.example.apptemple.displayMessage
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

//пример использования: checkUserData("qwerty", "123456", this)
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
//пример использования: checkEmail("5R5oA@example.com", this)
fun checkEmail(
    email : String, context : Context
) : Boolean = when {
    email.isBlank() || !email.contains("@") -> {
        context.displayMessage("Некорректная почта. Отсутствует @")
        false
    }
    else -> true
}
//проверка даты на валидность будет работать, начиная с андроид 8 и больше. Ниже 8 версии не поддерживают этот функционал
@RequiresApi(VERSION_CODES.O)//Пример использования: checkBirthdate("01.01.2000", this(Если используется в активити))
fun checkBirthdate(
    birthdate : String, context : Context
): String? = try {
    if (Regex("""^\d{2}\.\d{2}\.\d{4}$""").matches(birthdate)) {
        val date = LocalDate.parse(birthdate, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        when {
            date < LocalDate.now() -> birthdate
            else -> {
                context.displayMessage("Некорректная дата рождения")
                null
            }
        }
    } else null
} catch (e: DateTimeParseException) {
    context.displayMessage("Неправильный формат даты (дд.мм.гггг)")
    null
}