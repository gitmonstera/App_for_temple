package com.example.apptemple.domain

import com.example.apptemple.utils.Messages.INCORRECT_BIRTHDATE
import com.example.apptemple.utils.Messages.INCORRECT_DATA
import com.example.apptemple.utils.Messages.INCORRECT_EMAIL
import com.example.apptemple.utils.Messages.INCORRECT_LOGIN
import com.example.apptemple.utils.Messages.INCORRECT_PASSWORD
import com.example.apptemple.utils.Messages.SUCCESS

fun checkUserData(//null - означает, что этот параметр не был передан в функцию(нужно для авторизации). Для регистрации мы все передаем
    username : String, password : String, email : String? = null, birthdate : String? = null
) : String = when {
    username.isBlank() && checkPassword(password) -> INCORRECT_DATA
    username.isBlank() -> INCORRECT_LOGIN
    checkPassword(password) -> INCORRECT_PASSWORD
    //если мы не передали в функцию email, то пропускаем этот шаг. с условием про дату то же самое
    email != null && checkEmail(email) -> INCORRECT_EMAIL
    birthdate != null && !checkDate(birthdate) -> INCORRECT_BIRTHDATE
    else -> SUCCESS
}
fun checkPassword(password : String) = password.isBlank() || password.length < 6
// string.isBlank - проверка является ли строка пустой или состоит только из пробелов
fun checkEmail(email: String) = email.isBlank() && !email.contains("@")
//проверяет строку на соответствие регулярному выражению (дд.мм.гггг)
fun checkDate(birthdate: String) = Regex("""^\d{2}\.\d{2}\.\d{4}$""").matches(birthdate)