package com.customer.rewards.points.service;

import com.customer.rewards.points.constants.Constants;
import com.customer.rewards.points.entity.Transaction;
import com.customer.rewards.points.model.Rewards;
import com.customer.rewards.points.repository.CustomerRepository;
import com.customer.rewards.points.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService{

    @Autowired
    CustomerRepository customerRepository;

    public Rewards getRewardsByCustomerId(String customerId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*Constants.daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*Constants.daysInMonths);

        List<Transaction> transactions = customerRepository.findByCustomerId(customerId).getTransactions();
        List<Transaction> lastMonthTransactions = transactions.stream()
                .filter(s -> lastMonthTimestamp.after(Timestamp.from(Instant.now())))
                .collect(Collectors.toList());
        List<Transaction> lastSecondMonthTransactions = transactions.stream()
                .filter(s -> lastSecondMonthTimestamp.after(Timestamp.from(Instant.now())))
                .collect(Collectors.toList());
        List<Transaction> lastThirdMonthTransactions = transactions.stream()
                .filter(s -> lastThirdMonthTimestamp.after(Timestamp.from(Instant.now())))
                .collect(Collectors.toList());
        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
        Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

        Rewards customerRewards = new Rewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
        customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
        customerRewards.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

        return customerRewards;

    }

    private Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r));
    }

    private Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > Constants.firstRewardLimit && t.getTransactionAmount() <= Constants.secondRewardLimit) {
            return (long) Math.round(t.getTransactionAmount() - Constants.firstRewardLimit);
        } else if (t.getTransactionAmount() > Constants.secondRewardLimit) {
            return (long) (Math.round(t.getTransactionAmount() - Constants.secondRewardLimit) * 2
                                + (Constants.secondRewardLimit - Constants.firstRewardLimit));
        } else
            return 0l;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }
}
