const val TAVERN_NAME = "Taernyl's Folly"
fun main() {
    placeOrder("shandy,Dragon's Breath,5.91")
}

fun placeOrder(s: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Order to $tavernMaster")

    println("----------------------------------------")

    val data = s.split(',')
    val type = data[0]
    val name = data[1]
    val price = data[2]
    val message = "Purchase $name ($type) with $price"
    println(message)

    println("----------------------------------------")

    // 해체선언(destructuring declaration) 사용하기
    val (type2, name2, price2) = s.split(',')
    val message2 = "Purchase $name2 ($type2) with $price2"
    println(message2)

    println("----------------------------------------")

    val phrase = "와, $name 진짜 좋구나!"
    println("ohoh ${toDragonSpeak(phrase)}")
}

private fun toDragonSpeak(phrase: String) =
    phrase.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }
