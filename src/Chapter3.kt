fun main() {
    val name = "kkwon"
    var point = 100
    val isBlessed = true


    // CASE 1
    if (point == 100) {
        println(name + " good!")
    } else if (point >= 90) {
        if (isBlessed) {
            println(name + " good enough and better soon")
        } else {
            println(name + "good enough anyway")
        }
    } else {
        println(name + " bad")
    }

    // 조건 표현식(conditional expression)
    val status = if (point == 100) {
        "good!"
    } else {
        "bad"
    }
    println(name + " " + status)


    // CASE 2
    val isImmortal = false

    // 논리 연산자 쓰기
    if (isBlessed && point > 90 || isImmortal) {
        println("GREEN")
    } else {
        println("NONE")
    }

    // 조건 표현식
    val auraVisible = isBlessed && point > 50 || isImmortal
    val auraColor = if (auraVisible) "GREEN" else "NONE"
    println(auraColor)


    // When 표현식 + Range
    val healthStatus = when (point) {
        100 -> "Best"
        in 90..99 -> "Almost Best"
        in 75..89 -> if (isBlessed) {
            "A little bad but better soon"
        } else {
            "A little bad"
        }
        in 15..74 -> "Bad"
        else -> "Worst"
    }

    // 문자열 템플릿
    println(
        "(Aura: $auraColor) " +
                "(Blessed: ${if (isBlessed) "YES" else "NO"})"
    )
    println("$name $healthStatus")
}