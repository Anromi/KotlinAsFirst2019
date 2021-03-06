@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr

/**
 * Класс "комплексое число".
 *
 * Общая сложность задания -- лёгкая.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */

fun Complex(s: String): Complex {
    val list = mutableListOf<Double>()
    for (i in 0..1) {
        list.add(Regex("[+-]*[\\d.]*").findAll(s).elementAt(i).value.toDouble())
    }
    return Complex(list.first(), list.last())
}

class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Сложение.
     */
    operator fun plus(other: Complex): Complex = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(-re, -im)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(re * other.re - im * other.im, re * other.im + im * other.re)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex = Complex(
        (re * other.re + im * other.im) / (sqr(other.re) + sqr(other.im)),
        (-re * other.im + im * other.re) / (sqr(other.re) + sqr(other.im))
    )

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Complex

        if (re != other.re) return false
        if (im != other.im) return false

        return true
    }

    override fun hashCode(): Int {
        var result = re.hashCode()
        result = 31 * result + im.hashCode()
        return result
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String = when {
        im > 0 -> "$re+${im}i"
        im < 0 -> "$re${im}i"
        else -> "$re"
    }
}