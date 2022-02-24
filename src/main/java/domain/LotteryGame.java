package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.generatestrategy.LotteryGenerateFamily;

public class LotteryGame {

	private static final int LOTTERY_PRICE = 1000;

	private final int theNumberOfLottery;
	private final LotteryGenerateFamily lotteryGenerator;
	private Lotteries lotteries;
	private WinningLottery winningLottery;

	public LotteryGame(int theNumberOfLottery, LotteryGenerateFamily lotteryGenerator) {
		this.theNumberOfLottery = theNumberOfLottery;
		this.lotteryGenerator = lotteryGenerator;
		createAutoLottery();
	}

	private void createAutoLottery() {
		final List<List<LotteryNumber>> lotteriesNumber = new ArrayList<>();
		for (int i = 0; i < theNumberOfLottery; i++) {
			lotteriesNumber.add(lotteryGenerator.getNumbers());
		}
		lotteries = new Lotteries(lotteriesNumber);
	}

	public void createWinningLottery(final List<Integer> winningNumbers, final Integer bonusBall) {
		final List<LotteryNumber> winningLotteryNumbers = winningNumbers.stream()
			.map(LotteryNumber::new)
			.collect(Collectors.toList());
		winningLottery = new WinningLottery(winningLotteryNumbers, new LotteryNumber(bonusBall));
	}

	public Map<Rank, Integer> makeWinner() {
		return lotteries.getTheNumberOfWinners(winningLottery);
	}

	public double makeRankingPercent(final Map<Rank, Integer> rankResult) {
		int incomeRate = 0;
		for (Rank rank : rankResult.keySet()) {
			incomeRate += rankResult.get(rank) * rank.getPrize();
		}
		return (double)incomeRate / (theNumberOfLottery * LOTTERY_PRICE);
	}

	public List<Lottery> getLotteries() {
		return lotteries.getLotteries();
	}
}
