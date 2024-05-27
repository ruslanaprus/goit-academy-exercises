package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Account {
    private int customerId;
    private String name;
    private String status;
    private boolean isPrimary;

    public Account(int customerId, String name, String status, boolean isPrimary) {
        this.customerId = customerId;
        this.name = name;
        this.status = status;
        this.isPrimary = isPrimary;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName(){
        return name;
    }

    public String getStatus() {
        return status;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public static void main(String[] args) {

        String json = "{\n" +
                " \"customerId\": 111,\n" +
                " \"name\": \"bob\",\n" +
                " \"status\": \"active\",\n" +
                " \"isPrimary\": true\n" +
                "}";

        Account account = new Gson().fromJson(json, Account.class);
        System.out.println("account.getCustomerId() = " + account.getCustomerId());
        System.out.println("account.getName() = " + account.getName());
        System.out.println("account.getStatus() = " + account.getStatus());
        System.out.println("account.isPrimary() = " + account.isPrimary());

        Account newAccount = new Account(222, "Alice", "active", true);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonNew = gson.toJson(newAccount);
        System.out.println("jsonNew = " + jsonNew);

    }
}
