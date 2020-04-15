## 클래스 정의하기
```
class Player {
    fun castFireball(numFireblls: Int = 2) = 
        println("throw firballs (x$numFirballs)") 
}
```
```
fun main() {
    val player = Player()
    player.castFireball()
} 
```
### 가시성(visibility) & 캡슐화
* public
* private
* protected
* internal: 함수/속성이 정의된 클래스가 포함된 모듈에서 가능

자바의 패키지 가시성은 코틀린에 없다. 
#
### 클래스 속성
다음 코드는 에러가 난다. 
```
class Player {
    var name: String
}
```
변수와 달리 클래스 속성은 반드시 초기값이 지정되야 한다. 
#
속성에 대한 getter는 자동 생성 된다.

setter는 경우에 따라 다르다. var로 선언된 경우 자동 생성 된다. 

getter/setter는 overriding해서 새로 정의 할 수 있다. 
```
class Player {
    var name = "kkwon" // setter 추가하려면 val이 아닌 var로 되어야 한다 
        get() = field.capitalize()
        set(value) {
            field = value.trim()
        }

    ...
}
``` 
여기서 field 키워드는 코틀린이 관리하는 backing field를 참조한다. getter/setter에서만 사용된다. 
```
val player = Player()
player.name = "park   "
println(player.name + "TheBrave")
```
속성을 참조하면 자동으로 getter/setter가 호출된다. 
#
만약 속성은 public인데 setter는 숨기고 싶으면 private만 추가해주면 된다. 
```
private set(value) {
```
#
산출 속성(computed property) 이란게 있다.
```
class Dice() {
    val rolledValue 
        get() = (1..6).shuffled().first()
}
```
자신의 독자적인 값을 갖지 않기 때문에 backing field가 생성되지 않는다.
#
지금까지 해온 소스들을 클래스로 옮겨본다. 
```
package com.kkwonsy.nyethack

class Player {

    var name = "madrigal"
        get() = field.capitalize()
        private set(value) {
            field = value.trim()
        }

    var healthPoints = 89
    val isBlessed = true
    private val isImmortal = false

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmotal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    fun formatHealthStatus() =
        when (healthPoints) {
            100 -> "최상"
            in 90..99 -> "약간 찰과상"
            in 75..89 -> if (isBlessed) {
                "경미한 상처, 빠른 치유중"
            } else {
                "경미한 상처"
            }
            in 15..74 -> "많이 다침"
            else -> "최악"
        }

    fun castFireball(numFireballs: Int = 2) = 
        println("A fireball shows up. (x$numFireballs)")
}
```
#

서로 다른 패키지에 있는 같은 이름의 클래스나 함수를 사용하다보면 이름 충돌이 생길 수 있다. 

as 키워드를 사용하면 좋다. 
```
import com.io.extractValue
import com.util.extractValue as extractValueUtil
...

val value1 = extractValue()
val value2 = extractValueUtil()
``` 
#
var 과 val 내부 구현?
```
class Student(var name: String)

...
public final class Student {
    @NotNull
    private String name;

    @NotNull
    public final String getName() ...

    public final void setName(@NotNull String var1) ...

    public Stuent(@NotNull String name) ...
}
```
```
class Student(val name: String)

...
public final class Student {
    @NotNull
    private String name;

    @NotNull
    public final String getName() ...

    public Student(@NotNull String name) ...
}
```
val로 선언하니 setter가 없다. 

산출속성은?
```
Class Student {
    val name: String 
        get() = "Madrigal"
}
...

public final class Student {
    @NotNull
    public final String getName() ...
}
```
getter밖에 없다. 
#
### 경합 상태 방지하기
다음코드는 에러가 난다. 
```
class Weapon(val name: String)
class Player {
    var weapon: Weapon? = Wepon("Ebony Kris")
    
    fun printWeaponName() {
        if (weapon != null) {
            println(weapon.name) // 여기서 에러
        }
    }
}

fun main() {
    Player().printWeaponName()
}
```
분명 null이 아님을 확인한 후 실행을 했으나 에러가 발생한다. 

if 문에서 null 확인을 하면 코틀린 컴파일러는 null이 올 수 없는 Weapon 을 임시 변환해준다. (=smart casting)

근데 왜 에러가 날까?

경합상태(race condition)을 발생할 가능성이 있음을 코틀린 컴파일러가 알고 있기 때문이다. 
쉬운 말로 null 체크 후에 **weapon = null** 코드가 들어올 수 있다는 것을 컴파일러가 체크해 준다.

다음 코드로 변경해주면 잘 돌아간다.  
```
fun printWeaponName() {
    weapon?.also {
        println(it.name)
    }
}
```
it은 코드로 변경할 수 없기 때문에 컴파일러가 에러를 주지 않는다.
#

### 초기화 
 

  





