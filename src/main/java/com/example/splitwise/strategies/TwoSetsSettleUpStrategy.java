package com.example.splitwise.strategies;

import com.example.splitwise.models.Transaction;
import com.example.splitwise.models.User;
import org.springframework.data.util.Pair;

import java.util.*;

public class TwoSetsSettleUpStrategy implements SettleUpStrategy {

    @Override
    public List<Transaction> settleUp(Map<User, Double> aggregateExpenses) {

        Queue<Pair<User, Double>> minHeap = new PriorityQueue<>( new Comparator<Pair<User, Double>>() { // Ascending order (this will give biggest negative number first)
            @Override
            public int compare(Pair<User, Double> o1, Pair<User, Double> o2) {
                return o1.getSecond().compareTo(o2.getSecond());
            }
        });

        Queue<Pair<User, Double>> maxHeap = new PriorityQueue<>( new Comparator<Pair<User, Double>>() { // Descending order (this will give biggest positive number first)
            @Override
            public int compare(Pair<User, Double> o1, Pair<User, Double> o2) {
                return o2.getSecond().compareTo(o1.getSecond());
            }
        });

        // Add the negative numbers to minHeap and positive numbers to maxHeap accordingly
        for(Map.Entry<User, Double> entry : aggregateExpenses.entrySet()) {
            if(entry.getValue() < 0.0) {
                minHeap.add(Pair.of(entry.getKey(), entry.getValue()));
            }
            else {
                maxHeap.add(Pair.of(entry.getKey(), entry.getValue()));
            }
        }

        // Now poll the numbers from minHeap and maxHeap and create transactions accordingly
        List<Transaction> transactions = new ArrayList<>();
        while(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            Pair<User, Double> userToPay = minHeap.poll();
            Pair<User, Double> userToGet = maxHeap.poll();




        }






        return List.of();
    }

}
