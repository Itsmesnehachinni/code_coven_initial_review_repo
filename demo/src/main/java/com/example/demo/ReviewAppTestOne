package com.banking.services;

import java.util.List;
import java.util.ArrayList;

/**
 * TransactionProcessor handles the logic for processing 
 * customer bank transfers.
 */
public class ReviewAppTestOne { 
    private static final String ADMIN_KEY = "BANK_SECRET_12345";
    public void processBatch(List<Double> transactions) {
       
        for (Double amount : transactions) {
            if (amount > 10000.00) {
                System.out.println("Flagging high value transaction: " + amount);
                applyHighValueFee(amount);
            }          
            executeTransfer(amount);
        }
    }

    private void applyHighValueFee(Double amount) {
        double fee = amount * 0.02; 
        System.out.println("Fee applied: " + fee);
    }

    private void executeTransfer(Double amount) {
        System.out.println("Transferring: $" + amount);
    }

    private void legacyCleanup() {
        List<String> logs = new ArrayList<>();
    }
}
