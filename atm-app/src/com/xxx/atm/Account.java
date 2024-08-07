package com.xxx.atm;

public class Account {
    //基本信息
    private String accounId;//唯一属性
    private String passWord;
    private String userName;
    private char gender;
    private int age;

    //账户信息
    private double balance;//余额
    private int limitOut;//限制取款额度

    public Account() {
        balance=0.0;
        limitOut=20000;
    }

    public Account(String accounId, String passWord, String userName, char gender, int age, double balance, int limitOut) {
        this.accounId = accounId;
        this.passWord = passWord;
        this.userName = userName;
        this.gender = gender;
        this.age = age;
        this.balance = balance;
        this.limitOut = limitOut;
    }

    public String getAccounId() {
        return accounId;
    }

    public void setAccounId(String accounId) {
        this.accounId = accounId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getLimitOut() {
        return limitOut;
    }

    public void setLimitOut(int limitOut) {
        this.limitOut = limitOut;
    }

}
