package com.TyangyangT.www;
import java.io.*;

public class getDriveLetter extends Thread {
    File[] discs = File.listRoots();// 获取可用文件系统根

    @Override
    public void run() {
        System.out.println("系统正在检测可移动磁盘...");
        while (true) {
            File[] discstem = File.listRoots();
            boolean flag = false;
            if (discstem.length > discs.length) {
                for (int i = discstem.length - 1; i >= 0; i--) {
                    flag = false;
                    for (int j = discs.length - 1; j >= 0; j--) {
                        if (discs[j].equals(discstem[i])) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        System.out.println("插入可移动磁盘:" + discstem[i].toString());
                        deleteTheFile deleteTheFile = new deleteTheFile() ;
                        deleteTheFile.setpath(discstem[i].toString());
                        deleteTheFile.start();                       
                    }
                }
                discs = File.listRoots(); // 更新文件系统根
            } else if (discstem.length < discs.length) {
                for (int i = discs.length - 1; i >= 0; i--) {
                    flag = false;
                    for (int j = discstem.length - 1; j >= 0; j--) {
                        if (discs[i].equals(discstem[j])) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        boolean flag2 = deleteTheFile.getflag();
                        if (!flag2) {
                            System.out.println("程序未执行完毕，数据可能丢失！！！");
                        }
                        System.out.println("退出可移动磁盘:" + discs[i].toString());
                    }
                }
                discs = File.listRoots();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}