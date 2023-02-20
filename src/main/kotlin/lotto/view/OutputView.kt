package lotto.view

import lotto.domain.Lottery
import lotto.domain.Rank
import lotto.domain.RankCounter
import java.lang.StringBuilder

class OutputView {
    fun printLotteries(lotteries: List<Lottery>) {
        println("${lotteries.size}$NUMBER_OF_LOTTERY_GUIDE")
        lotteries.forEach { lottery ->
            println(lottery.numbers.map { it.number }.sorted())
        }
    }

    fun printWinningResult(counter: RankCounter, profit: Double) {
        println(WINNING_RESULT_GUIDE)

        var rankAnnouncement = StringBuilder()
        counter.numberOfEachRank.forEach { (name, count) ->
            rankAnnouncement.insert(0, getRankAnnouncement(name, count))
        }
        print(rankAnnouncement)

        println("${TOTAL_PROFIT_GUIDE.format(profit)}${Revenue.valueOf(profit).description}")
    }

    private fun getRankAnnouncement(name: String, count: Int): String {
        if (name == "MISS") return ""

        val rank = Rank.valueOf(name)
        val announcement = StringBuilder()
        announcement.append(COUNT_OF_MATCH_DESCRIPTION.format(rank.countOfMatch))
        if (rank == Rank.SECOND) {
            announcement.append(BONUS_MATCH_DESCRIPTION)
        }
        announcement.append(PRIZE_AND_COUNT_RESULT_DESCRIPTION.format(rank.winningMoney, count))
        return announcement.toString()
    }

    enum class Revenue(val description: String) {
        LOSS("(기준이 1이기 때문에 결과적으로 손해라는 의미임)"),
        NOTHING("(기준이 1이기 때문에 결과적으로 손해도 이득도 아니라는 의미임)"),
        GAIN("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");

        companion object {
            private const val STANDARD_VALUE = 1

            fun valueOf(profit: Double): Revenue {
                if (profit < STANDARD_VALUE) return LOSS
                if (profit > STANDARD_VALUE) return GAIN
                return NOTHING
            }
        }
    }

    fun printInterval() {
        println()
    }

    companion object {
        private const val BONUS_MATCH_DESCRIPTION = ", 보너스 볼 일치"
        private const val COUNT_OF_MATCH_DESCRIPTION = "%d개 일치"
        private const val NUMBER_OF_LOTTERY_GUIDE = "개를 구매했습니다."
        private const val PRIZE_AND_COUNT_RESULT_DESCRIPTION = " (%d원) - %d개\n"
        private const val TOTAL_PROFIT_GUIDE = "총 수익률은 %.2f입니다."
        private const val WINNING_RESULT_GUIDE = "당첨 통계\n" +
            "---------"
    }
}
