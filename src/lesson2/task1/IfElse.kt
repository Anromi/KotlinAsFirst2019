@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt
/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return when {
        (age % 10 == 1) && (age != 11) && (age % 100 != 11) -> "$age год"
        (5 > age % 10) && (age % 10 > 1) && (age !in 12..14) -> "$age года"
        else -> "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s = (v1 * t1 + v2 * t2 + v3 * t3) / 2
    return when {
        s < v1 * t1 -> s / v1
        s > v1 * t1 + v2 * t2 -> t1 + t2 + (s - v1 * t1 - v2 * t2) / v3
        else -> t1 + (s - v1 * t1) / v2
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    return when {
        ((kingX != rookX1) && (kingY != rookY1)) && ((kingX != rookX2) && (kingY != rookY2)) -> 0
        ((kingX == rookX1) || (kingY == rookY1)) && ((kingX != rookX2) && (kingY != rookY2)) -> 1
        ((kingX != rookX1) && (kingY != rookY1)) && ((kingX == rookX2) || (kingY == rookY2)) -> 2
        else -> 3

    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val q1 = (kingX - kingY) == (bishopX - bishopY)
    val q2 = (kingX + kingY) == (bishopX + bishopY)
    val q3 = kingX == rookX
    val q4 = kingY == rookY
    return when {
        !q1 && !q2 && !q3 && !q4 -> 0
        !q1 && !q2 && (q3 || q4) -> 1
        (q1 || q2) && !q3 && !q4 -> 2
        else -> 3
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val w1: Double
    val w2: Double
    val w3: Double
    when {
        a == maxOf(a, b, c) -> {
            w1 = a
            w2 = b
            w3 = c
        }
        b == maxOf(a, b, c) -> {
            w1 = b
            w2 = a
            w3 = c
        }
        else -> {
            w1 = c
            w2 = a
            w3 = b
        }
    }
    if (a + b < c || b + c < a || a + c < b) {
        return -1
    }
    return when {
        sqr(w2) + sqr(w3) > sqr(w1) -> 0
        sqr(w2) + sqr(w3) == sqr(w1) -> 1
        else -> 2
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    val r1: Int = b - c
    val r2: Int = d - a
    val r3: Int = d - c
    val r4: Int = b - a
    val r5: Int = c - b
    val r6: Int = if (b > c) r1 else r5
    val r7: Int = max(a, b) - min(d, c)
    val r8: Int = max(d, c) - min(a, b)
    return when {
        c in (a + 1) until b && b < d -> r1
        a in (c + 1) until d && d < b -> r2
        d in (a + 1) until b -> r3
        b in (c + 1) until d -> r4
        (a == c && b == d) -> r6
        (b == d) -> r7
        (a == c) -> r8
        c == b -> r1
        d == a -> r2
        else -> -1
    }
}
