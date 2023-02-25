package lotto.domain

class Lottery(
    val numbers: List<LotteryNumber>
) {
    init {
        checkLotteryNumbersSize()
        checkNumbersDuplicate()
    }

    private fun checkLotteryNumbersSize() {
        require(numbers.size == LOTTERY_NUMBER_SIZE) {
            "$LOTTERY_NUMBER_SIZE_ERROR\n" +
                "오류값 : ${numbers.map { it.toInt() }}"
        }
    }

    private fun checkNumbersDuplicate() {
        require(numbers.size == numbers.distinct().size) {
            "$LOTTERY_NUMBERS_DUPLICATE_ERROR\n" +
                "오류값 : ${numbers.map { it.toInt() }}"
        }
    }

    fun countMatches(winningLottery: Lottery): Int = winningLottery.numbers.count { numbers.contains(it) }

    fun containBonusNumber(number: LotteryNumber): Boolean {
        return numbers.contains(number)
    }

    companion object {
        private const val LOTTERY_NUMBER_SIZE = 6
        private const val LOTTERY_NUMBER_SIZE_ERROR = "로또 번호는 6개여야 합니다."
        private const val LOTTERY_NUMBERS_DUPLICATE_ERROR = "6개의 로또번호는 서로 중복되지 않아야 합니다."

        fun from(numbers: List<Int>): Lottery = Lottery(numbers.map { LotteryNumber.from(it) })
    }
}
