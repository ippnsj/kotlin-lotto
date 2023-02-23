package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryNumberTest {
    @ParameterizedTest(name = "로또 번호는 {0} 이다")
    @ValueSource(ints = [1, 45])
    fun `번호를 하나 가진다`(number: Int) {
        val lotteryNumber = LotteryNumber.from(number)
        assertThat(lotteryNumber.toInt()).isEqualTo(number)
    }

    @ParameterizedTest(name = "로또 번호는 {0} 일 수 없다")
    @ValueSource(ints = [0, 46])
    fun `번호는 1 이상 45 이하의 숫자가 아니라면 에러가 발생한다`(number: Int) {
        assertThrows<IllegalArgumentException> {
            LotteryNumber.from(number)
        }
    }

    @ParameterizedTest(name = "로또 번호는 {0} 일 수 있다")
    @ValueSource(ints = [1, 45])
    fun `번호는 1 이상 45 이하의 숫자면 에러가 발생하지 않는다`(number: Int) {
        assertDoesNotThrow {
            LotteryNumber.from(number)
        }
    }
}
