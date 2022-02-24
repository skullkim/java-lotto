package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lotteries {

	private static final int INITIAL_RANKS = 0;

	private final List<Lottery> lotteries;

	public Lotteries(final List<List<LotteryNumber>> lotteriesNumber) {
		lotteries = new ArrayList<>();
		lotteriesNumber.forEach(lotteryNumber ->
			lotteries.add(new Lottery(lotteryNumber)));
	}

	public List<Lottery> getLotteries() {
		return lotteries;
	}

	public Map<Rank, Integer> getTheNumberOfWinners(final WinningLottery winningLottery) {
		Map<Rank,Integer> winners = initRankResult();
		lotteries.forEach(lottery -> {
			Rank rank = winningLottery.getRank(lottery);
			putCountToWinner(winners, rank);
		});
		return winners;
	}

	private Map<Rank, Integer> initRankResult() {
		final Map<Rank, Integer> rankResult = new HashMap<>();
		Rank.getValues()
			.forEach((rank) -> rankResult.put(rank, INITIAL_RANKS));
		return rankResult;
	}

	private void putCountToWinner(Map<Rank, Integer> winners, Rank rank) {
		if (!rank.equals(Rank.NONE)) {
			winners.put(rank, winners.get(rank) + 1);
		}
	}

}
