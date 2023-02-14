package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `구입금액을 가진다`(amount: Int) {
        val purchaseAmount = PurchaseAmount(amount)
        assertThat(purchaseAmount.amount).isEqualTo(amount)
    }

    @ParameterizedTest
    @ValueSource(ints = [1000, 50000])
    fun `구입금액이 1000원 이상이고 5만원 이하면 에러가 발생하지 않는다`(amount: Int) {
        assertDoesNotThrow {
            PurchaseAmount(amount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [999, 900, 51000, 50001])
    fun `구입금액이 1000원 이상이 아니고 5만원을 초과하면 에러가 발생한다`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            PurchaseAmount(amount)
        }
    }
}
