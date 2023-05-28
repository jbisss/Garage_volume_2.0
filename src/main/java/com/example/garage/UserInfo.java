package com.example.garage;

public class UserInfo {

    private static String name;
    private static int balance;
    private static int id;

    public static String getName() {
        return name;
    }

    public static int getBalance() {
        return balance;
    }

    public static void setName(String name) {
        UserInfo.name = name;
    }

    public static void setBalance(int balance) {
        UserInfo.balance = balance;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserInfo.id = id;
    }
}
