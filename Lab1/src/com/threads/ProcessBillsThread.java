package com.threads;

import com.model.Bill;
import com.model.Supermarket;

import java.util.List;

/**
 * This record is parsing all the bills in the supermarket
 */
public record ProcessBillsThread(List<Bill> bills, Supermarket supermarket, int threadNumber) implements Runnable {
    @Override
    public void run() {
        for (Bill bill : bills) {
            System.out.println("Thread " + threadNumber + " is processing a bill");
            supermarket.parseBill(bill);
        }
    }
}