package com.TyangyangT.www;

import java.io.File;

public class deleteTheFile extends Thread {
    private String path;
    public void setpath(String path){
        this.path = path;
    }

    private static boolean flag = false;
    public static  boolean getflag(){
        return deleteTheFile.flag;
    }

    @Override
    public void run(){
        System.out.println("文件删除开始执行操作······");
        File file = new File(path);     //U盘路径
        File[] files = file.listFiles();    //获取U盘目录下所有文件
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
            if (files[i].getName().equals("Monitoring_experts_Auto_Close_don't_eject")) {
                System.out.println("停止程序。");
                System.exit(0);
            }
        }
        try {
          for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                File[] filesTemp = new File(files[i].getPath()).listFiles();
                if (filesTemp != null) {
                    for (int j = 0; j < filesTemp.length; j++) {
                        filesTemp[j].delete();
                    }
                    files[i].delete(); 
                }else{
                    System.out.println(files[i]);
                    files[i].delete();
                }
            } else {
                files[i].delete();
            }
          }
        } catch (SecurityException e) {
            System.out.println("运行时发生了错误！！！");
            System.out.println(e);
        }finally{
            deleteTheFile.flag = true;
            System.out.println("操作执行完成。");
            interrupt();
        }
    }
}