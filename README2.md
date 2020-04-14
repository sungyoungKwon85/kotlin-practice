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



