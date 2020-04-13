fun main() {

    // 코틀린 표준 라이브러리의 함수 중 하나
    val numLetters = "Mississippi".count()
    println(numLetters)

    // 특정 문자 개수를 알고 싶다면?
    val numLetters2 = "Mississippi".count { letter -> letter == 's'} // 괄호는 생략 가능
//        "Mississippi".count ({ letter -> letter == 's'})
    println(numLetters2)

    println("################################################")

    // 익명함수 정의하고 호출하기
    println({
        val currentYear = 2020
        "Welcome to Seoul! in $currentYear"
    }()) // ()를 사용해서 호출한거임

    println("################################################")

    // 익명함수도 타입을 가진다 = 함수타입
    var greetingFunction: () -> String = {
        val currentYear = 2020
        "Welcome to Seoul! in $currentYear"
    }
    // 리턴 키워드가 없어도 String이 반환된다.
    // 암시적 반환이라고 하는데 익명함수에서는 return 키워드 사용이 금지되어 있음(어떤 곳으로 복귀하는지 컴파일러가 알 수 없음)
//    var greetingFunction2: () -> String = {
//        val currentYear = 2020
//        return "Welcom to Seoul! in $currentYear" // 에러남
//    }

    println(greetingFunction())


    println("################################################")

    // 인자 사용하기
    val greetingFunction3: (String) -> String = {name ->
        val currentYear = 2020
        "Welcome to Seoul, $name! in $currentYear"
    }

    // it 키워드 사용하기 (다만 데이터가 무엇인지 알기 어려울 수 있으니 중첩된 익명함수에서는 매개변수 이름을 쓰자)
    val greetingFunction4: (String) -> String = {
        val currentYear = 2020
        "Welcome to Seoul, $it! in $currentYear"
    }
    println(greetingFunction3("kkwon"))
    println(greetingFunction4("kkwon"))

    // 다중 인자받기
    val greetingFunction5: (String, Int) -> String = { name, year ->
        "Welcome to Seoul, $name! in $year"
    }
    println(greetingFunction5("kkwon", 2020))

    // 타입추론을 이용한 생략
    val greetingFunction6 = { name: String, year: Int ->
        "Welcome to Seoul, $name! in $year"
    }
    println(greetingFunction6("kkwon", 2020))

    println("################################################")

    // 함수를 인자로 받는 함수
    runSimulation("kwon", greetingFunction6)

    // 매개변수인 함수를 구현하면서 전달해도됨
    runSimulation("kwon") {
        name, year ->
        "Welcome to Seoul, $name! in $year"
    }

    println("################################################")

    // 함수 참조 사용하기
    runSimulation2(
        "kwon",
        ::printDays, // 요기서 지정함
        greetingFunction6
    )

    println("################################################")

    // 반환타입으로 함수 사용하기
    runSimulation3()

    // 클로저 변수 스코프 확인
    runSimulation4()
}

// 반환타입으로 함수 사용하기
fun runSimulation3() {
   val greetingFunction = configureGreetingFunction()
    println(greetingFunction("kwon"))
}

// 반환타입으로 함수 사용하기
fun configureGreetingFunction(): (String) -> String {
    val type = "Lunar"
    var month = 4
    return { name: String ->
        val year = 2020
        month+= 1
        "Mr.$name, Date: $year $month($type)"
    }
}


// 함수를 인자로 받는 함수
fun runSimulation(name: String, greetingFunction: (String, Int) -> String) {
    val random = (1990..2020).shuffled().last()
    println(greetingFunction(name, random))
}

// 함수 참조 사용하기
inline fun runSimulation2(name: String,
                          dayPrinter: (Int) -> Unit,
                          greetingFunction: (String, Int) -> String) {
    val random = (1990..2020).shuffled().last()
    dayPrinter(random)
    println(greetingFunction(name, random))
}

// 함수 참조 사용하기
fun printDays(year: Int) {
    val dayPerYear = 365
    println("All days: ${dayPerYear * year}")
}

// 클로저 변수 스코프 확인
fun runSimulation4() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("kwon"))
    println(greetingFunction("kwon"))
}