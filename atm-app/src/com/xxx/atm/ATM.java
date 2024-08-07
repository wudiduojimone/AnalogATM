package com.xxx.atm;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ATM {
    ArrayList<Account> accountList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Account curAcc;

    public ATM() {
    }

    public void start() {
        while (true) {
            System.out.println("=======================");
            System.out.println("=====欢迎进入ATM系统======");
            System.out.println("1、用户开户");
            System.out.println("2、用户登录");
            System.out.println("请选择操作:");
            int opNum = sc.nextInt();
            switch (opNum) {
                case 1:
                    this.newAccount();
                    break;
                case 2:
                    this.login();
                    this.showOperator();
                    break;
                default:
                    System.out.println("非法操作");
            }
        }
    }

    private void showOperator() {
        while (true) {
            System.out.println("------" + "尊敬的" + curAcc.getUserName().charAt(0) + (curAcc.getGender() == '男' ? "先生" : "女士") + ",欢迎进入操作界面------");
            System.out.println("1、查看余额");
            System.out.println("2、存款");
            System.out.println("3、取款");
            System.out.println("4、转账");
            System.out.println("5、退出");
            int opNum = sc.nextInt();
            switch (opNum) {
                case 1:
                    showBalance();
                    break;
                case 2:
                    saveMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    transferMoney();
                    break;
                case 5:
                    curAcc = null;
                    return;
                default:
                    System.out.println("输入非法，请重新输入：");
            }
        }
    }

    private void transferMoney() {
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("请输入转账账户：");
            String transferID = sc.next();
            if (exist(transferID)) {
                Account targetAcc = getAccountByID(transferID);
                while (true) {
                    System.out.println("请输入转账金额:");
                    int transferMoney = sc.nextInt();
                    if (transferMoney > 0 && transferMoney <= curAcc.getBalance()) {
                        targetAcc.setBalance(targetAcc.getBalance() + transferMoney);
                        curAcc.setBalance(curAcc.getBalance() - transferMoney);
                        System.out.println("转账成功，您当前余额为： " + curAcc.getBalance());
                        break;
                    }
                    System.out.println("输入非法，请重新输入");
                }
                break;
            } else {
                System.out.println("您输入的账户不存在，请重新输入：");
            }
        }

    }

    private void withdrawMoney() {
        while (true) {
            System.out.println("-----------------------------");
            System.out.println("请输入您需要取款多少钱：");
            int withDrawMoney = sc.nextInt();
            if (withDrawMoney > 0 && withDrawMoney <= curAcc.getLimitOut()) {
                curAcc.setBalance(curAcc.getBalance() - withDrawMoney);
                break;
            } else if (withDrawMoney > curAcc.getLimitOut()) {
                System.out.println("取款金额超出限额，请重新输入");
            } else {
                System.out.println("输入非法，请重新输入");
            }
        }
        System.out.println("取款成功，您当前的余额为：" + curAcc.getBalance() + "元");
    }

    private void saveMoney() {
        System.out.println("请输入您需要存款多少钱：");
        while (true) {
            int addedMoney = sc.nextInt();
            if (addedMoney > 0) {
                curAcc.setBalance(curAcc.getBalance() + addedMoney);
                break;
            }
            System.out.println("输入非法，请重新输入");
        }
        System.out.println("存款成功，您当前的余额为：" + curAcc.getBalance() + "元");
    }

    private void showBalance() {
        System.out.println("您当前的余额为：" + curAcc.getBalance() + "元");
    }

    private void login() {
        while (true) {
            System.out.println("-----登陆界面-----");
            String id;
            System.out.println("请输入登录户号：");
            id = sc.next();

            System.out.println("请输入登陆密码：");
            String passWord = sc.next();
            if (checkLogin(id, passWord)) {
                System.out.println("恭喜您登陆成功！");
                curAcc = getAccountByID(id);
                break;
            } else {
                System.out.println("您输入的账号或者密码错误，请重新登录！");
            }
        }

    }

    private Account getAccountByID(String id) {
        for (Account acc : accountList) {
            if (acc.getAccounId().equals(id)) {
                return acc;
            }
        }
        return null;
    }

    private boolean checkLogin(String id, String passWord) {
        for (Account account : accountList) {
            String verifyID = account.getAccounId(), verifyPassWord = account.getPassWord();
            if (verifyID.equals(id) && verifyPassWord.equals(passWord)) {
                return true;
            }
        }
        return false;
    }

    private void newAccount() {
        Account account = new Account();
        System.out.println("请输入您的姓名：");
        account.setUserName(sc.next());

        while (true) {
            System.out.println("请输入您的性别：");
            char sex = sc.next().charAt(0);
            if (sex == '男' || sex == '女') {
                account.setGender(sex);
                break;
            }
            System.out.println("输入性别不合法，请重新输入：");
        }

        while (true) {
            System.out.println("请输入您的年龄：");
            int age = sc.nextInt();
            if (age < 18) {
                System.out.println("未满18岁不能开户");
            } else if (age > 150) {
                System.out.println("年龄非法");
            } else {
                account.setAge(age);
                break;
            }
        }

        while (true) {
            System.out.println("请输入密码：");
            String password = sc.next();
            System.out.println("请再次确认密码：");
            String verifyPassword = sc.next();
            if (password.equals(verifyPassword)) {
                account.setPassWord(password);
                break;
            }
            System.out.println("前后输入密码不一致，请重新输入");
        }

        while (true) {
            System.out.println("默认取款限制为20000，是否需要重新设置，输入是/否");
            String input = sc.next();
            if (input.length() != 1) {
                System.out.println("输入非法，请重新输入");
                continue;
            }
            char resetLimt = input.charAt(0);
            if (resetLimt != '是' && resetLimt != '否') {
                System.out.println("输入非法，请重新输入");
                continue;
            }
            if (resetLimt == '否') {
                break;
            }
            while (true) {
                System.out.println("请输入限额：");
                int limit = sc.nextInt();
                if (limit > 0) {
                    account.setLimitOut(limit);
                    break;
                }
                System.out.println("输入非法，请重新输入:");
            }
            break;
        }
        String accountID = getNewAccID();
        account.setAccounId(accountID);
        accountList.add(account);

        System.out.println("恭喜您开户成功，您的户号是：" + accountID + ",账号信息如下：");
        System.out.println("姓名：" + account.getUserName());
        System.out.println("性别：" + account.getGender());
        System.out.println("年龄：" + account.getAge());
        System.out.println("余额：" + account.getBalance());
        System.out.println("限额：" + account.getLimitOut());


    }

    private String getNewAccID() {
        Random rn = new Random();
        String accID;

        while (true) {
            accID = "";
            for (int i = 0; i < 10; i++) {
                accID += rn.nextInt(10);
            }
            if (!exist(accID)) break;
        }
        return accID;
    }

    private boolean exist(String accID) {
        for (Account acc : accountList) {
            if (accID.equals(acc.getAccounId())) {
                return true;
            }
        }
        return false;
    }

    private void printAllAcc() {
        for (Account account : accountList) {
            System.out.println("-------------------");
            System.out.println("姓名：" + account.getUserName());
            System.out.println("性别：" + account.getGender());
            System.out.println("年龄：" + account.getAge());
            System.out.println("余额：" + account.getBalance());
            System.out.println("限额：" + account.getLimitOut());
        }
    }

}
