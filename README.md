# kotlin-practice
빅 너드 랜치의 코틀린 프로그래밍 책을 보며 실습함.
#


### Hello world
```
fun main(args: Array<String>) {
   println("Hello world");
}
```
#


### REPL
파일을 생성하지 않고 코드를 빨리 테스트하는 도구

`IntelliJ -> Tools -> Kotlin -> Kotlin REPL 선택`
#

### JVM에서 실행하기
코틀린 소스 -> (컴파일러) -> 바이트 코드 <- JVM
#

코틀린은 JVM 뿐만 아니라 자바스크립트로도 컴파일 될 수 있으며 특정 플랫폼(Windows, Mac, Linux 등) 에서도 실행되는 Native Binary로도 컴파일 될 수 있다. 
#



## 변수, 상수, 타입
```
fun main() {
    var experiencePoints: Int = 5

    // 틀린 타입으로 지정하면 compile error
//    var errorType: Int = "String" 

    // 연산 가능
    experiencePoints += 5 

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
```
#


val 변수는 진정한 constant(상수)는 아니다. 다른 값을 반환하는 특별한 경우가 있다.

절대 변경하지 않는 변수는 컴파일 시점 상수 사용을 고려하자
`const val MAX_EXPERIENCE = 5000`
#


```
val player = "kkwon"
val point:Int = 5
```
위 변수 선언을 바이트코드로 살펴보면 아래처럼 타입이 추가되고, Int가 primitive 타입인 int로 변경되어 있음을 볼 수 있다.  
```
String player = "kkwon";
int point = 5
```
기본타입이 성능 및 기타 몇가지 측면에서 장점을 제공하기 때문에 컴파일시 변경해준다. 
코틀린은 내부적으로 참조 타입을 사용해서 객체지향적으로 코딩할 수 있게 해주며
컴파일시점에서 기본타입으로 변경해서 성능도 제공해 준다. 
#


## 조건문과 조건식
```
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
```
#


## 함수
코틀린의 기본 가시성제한자(visibility modifier)는 public이다.
#

```
private fun foramtStatus(point: Int = 2, isBleassed: Boolean): String =
    when(points)
        100 -> "Best"
        else -> "Worst"
```
매개변수에 기본값을 지정할 수도 있다.

게다가 return 생략해도 String이 리턴된다

또한 단인 표현식으로 중괄호를 안쓸 수도 있다

여기선 생략 안했지만 리턴타입(String)도 생략해도된다

리턴타입과 리턴문도 없고 단일 표현식을 쓰면 Unit 함수라고 부른다.(리턴안함) 
#


`printStatus("None", true, "kkwon", status)`

이렇게 호출하는 함수를 아래와 같이 매개변수 이름과 함께 순서를 바꿔 호출도 가능하다. 코드가 명확해진다.  

```
printStatus(status = status,
            isBlessed = true,
            name = "kwon",
            auraColor = "NONE")
```   
#

### Nothing 타입
앞서의 Unit 타입처럼 반환하지 않는데 차이점이 있다.
 
제어가 복귀되지 않는다.

```
// 내장함수 중 TODO라는게 있음
@kotlin.internal.InlineOnly
public inline fun TODO(): Nothing = throw NotImplementedError()

fun shouldReturnAString(): String {
    TODO("....")
}
``` 
이렇게 TODO를 써놓으면 Nothing이 반환되는데 이는 즉 제어가 복귀되지 않아 그냥 넘어가버리고 다음 코드가 실행되게 된다. 
(컴파일 에러 안남)
#

### 백틱 함수 이름
```
fun `**~prolly not a good idea!~**` () {
    ...
}
```
특수 문자를 사용하면 이렇게 문장처럼 쓸 수 있다. 

자바와 코틀린이 예약어가 다르기 때문에 (예를 들면 is()... 코틀린에서는 예약어지만 자바에서는 쓸 수 있음)
이름 충돌을 막기 위해 사용할 수 있다.

```
fun doSomthing() {
    `is`() // 자바의 is 메서드를 코틀린에서 호출
}
```
여튼 테스트 코드를 더 쉽게 나타내기에 좋다. 뭐.. 굳이 안써도됨 
#

## 익명 함수와 함수 타입 
```
// 코틀린 표준 라이브러리의 함수 중 하나
    val numLetters = "Mississippi".count()
    println(numLetters)

    // 특정 문자 개수를 알고 싶다면?
    val numLetters2 = "Mississippi".count { letter -> letter == 's'} // 괄호는 생략 가능
//        "Mississippi".count ({ letter -> letter == 's'})
    println(numLetters2)
```
#

### 직접 익명함수 만들어보기
```
fun main() {
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


}


// 함수를 인자로 받는 함수
fun runSimulation(name: String, greetingFunction: (String, Int) -> String) {
    val random = (1990..2020).shuffled().last()
    println(greetingFunction(name, random))
}
```
#

람다를 정의하면 JVM에서 객체로 생성이 됨

또한 JVM은 람다를 사용하는 모든 변수의 메모리 할당을 수행하므로 메모리가 많이 사용된다

inline을 사용하면 최적화를 할 수 있다

```
inline fun runSimulation(name: String, greetingFunction: (String, Int) -> String) {
    val random = (1990..2020).shuffled().last()
    println(greetingFunction(name, random))
} 
```
inline 키워드를 추가하면 컴파일러가 바이트코드 생성시 람다 코드를 함수 안으로 넣어버린다. 

람다를 인자로 받는 재귀함수는 코드가 무수히 많아질 수 있으니 컴파일러가 인라인 처리 하지 않고 루프 형태로 변경해버린다. 
#

함수 참조를 이용해 호출도 가능하다
```
    ...
    // 함수 참조 사용하기
    runSimulation2(
        "kwon",
        ::printDays, // 요기서 지정함
        greetingFunction6
    )
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
```
#

반환 타입으로 함수 타입을 사용할 수도 있다. 
```
    ...
    // 반환타입으로 함수 사용하기
    runSimulation3()

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
```
configureGreetingFunction에 선언된 지역변수인 type 및 month가 반환하는 람다에서 사용되었다. 

코틀린의 람다는 클로저이다. 

다른 함수에 포함된 함수에서 자신을 포함하는 함수의 매개변수와 변수를 사용할 수 있는 것을 클로저라고 한다. 

예제에서 month는 var로 선언했는데 반환하는 함수에서는 별도의 객체로 저장되어 사용된다.
예를 들어보자. 
```
// 클로저 변수 스코프 확인
fun runSimulation4() {
    val greetingFunction = configureGreetingFunction()
    println(greetingFunction("kwon"))
    println(greetingFunction("kwon"))
}

...

Mr.kwon, Date: 2020 5(Lunar)
Mr.kwon, Date: 2020 6(Lunar)
``` 
configureGreetingFunction에서 month는 4이지만 반환한 별도의 month는 따로 만들어져서 점점 증가했다. 
#

## null 안전과 예외 



  