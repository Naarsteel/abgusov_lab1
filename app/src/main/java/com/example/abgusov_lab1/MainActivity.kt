package com.example.abgusov_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация UI элементов
        val dayNumberInput = findViewById<EditText>(R.id.dayNumberInput)
        val showDayButton = findViewById<Button>(R.id.showDayButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Обработчик нажатия кнопки
        showDayButton.setOnClickListener {

            hideKeyboard()

            try {
                // Получаем введенный номер дня
                val dayNumber = dayNumberInput.text.toString().toInt()

                // Проверяем корректность ввода
                if (dayNumber in 1..7) {
                    // Получаем название дня недели
                    val dayName = getDayOfWeek(dayNumber)

                    // Отображаем результат
                    resultTextView.text = "День недели: $dayName"
                } else {
                    // Показываем сообщение об ошибке
                    Toast.makeText(
                        this,
                        "Пожалуйста, введите число от 1 до 7",
                        Toast.LENGTH_SHORT
                    ).show()
                    resultTextView.text = ""
                }
            } catch (e: NumberFormatException) {
                // Обработка случая, когда ввод не является числом
                Toast.makeText(
                    this,
                    "Пожалуйста, введите число",
                    Toast.LENGTH_SHORT
                ).show()
                resultTextView.text = ""
            }
        }
    }

    private fun getDayOfWeek(dayNumber: Int): String {
        return when (dayNumber) {
            1 -> "Понедельник"
            2 -> "Вторник"
            3 -> "Среда"
            4 -> "Четверг"
            5 -> "Пятница"
            6 -> "Суббота"
            7 -> "Воскресенье"
            else -> throw IllegalArgumentException("Некорректный номер дня")
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}