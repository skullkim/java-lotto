package domain.generatestrategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Lottery;
import domain.LotteryNumber;

public class LotteryRandomGeneratorStrategy implements LotteryGenerateStrategy {

	private final List<LotteryNumber> numbers;

	public LotteryRandomGeneratorStrategy() {
		numbers = IntStream.rangeClosed(LotteryNumber.NUMBER_MIN_RANGE, LotteryNumber.NUMBER_MAX_RANGE)
			.mapToObj(LotteryNumber::new)
			.collect(Collectors.toList());
	}

	public Lottery getNumbers() {
		Collections.shuffle(numbers);
		List<LotteryNumber> lotteryNumbers =  numbers.stream()
			.limit(Lottery.LOTTERY_SIZE)
			.sorted()
			.collect(Collectors.toList());
		return new Lottery(lotteryNumbers);
	}
}
