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


## 익명 함수와 함수 타입 









