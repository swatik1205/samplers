package com.customer.rewards.points.service;

import com.customer.rewards.points.model.Rewards;

public interface RewardsService {
    public Rewards getRewardsByCustomerId(String customerId);
}
