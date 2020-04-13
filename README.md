# kotlin-practice
빅 너드 랜치의 코틀린 프로그래밍 책을 보며 실습합니다.
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








