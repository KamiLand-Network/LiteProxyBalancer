package net.kamiland.liteproxybalancer_bungee.exception;

import net.kamiland.liteproxybalancer_bungee.api.expansion.Balancer;

public class BalancerPriorityConflictException extends RuntimeException {

    public BalancerPriorityConflictException(byte priority, Balancer balancer) {
        super("priority: " + priority + ", balancer: " + balancer);
    }

}
