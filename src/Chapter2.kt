fun main() {
    var experiencePoints: Int = 5
//    var errorType: Int = "String" // compile error

    experiencePoints += 5 // 연산 가능

    // kotlin types
    var str: String = "String"
    var c:Char = 's'
    var bb:Boolean = false
    var dd:Double = 3.14
    var llist:List<Int> = listOf(1,2,3)
    var sset:Set<Int> = setOf(1,2,3)
    var mmap:Map<Int, Int> = mapOf(0 to 0, 1 to 1)

    val readOnly: String = "can't change it"
    var changeable = "can change it" // 타입 추론, 타입 선언 생략이 가능, 컴파일러가 추론함

//    readOnly = "try to change" // compile error
    changeable = "try to change"
}