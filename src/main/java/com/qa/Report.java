package com.qa;

import com.qa.utils.GsonJsonUtil;

import java.io.File;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * IAT @wkzf
 * Created by yu on 2017/7/19.
 */
public class Report {
    /**
     * 用于生成内部报告
     * 因为 调用者来源未知,
     * 需要做统计
     */
    public Report() {

    }

    public static  void printTree(String path){
        displayDir(new File(path), "");
    }
    public static void printTreeList(String path){
        LinkedList list = new LinkedList();
        File dir = new File(path);
        File file[] = dir.listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isDirectory())
                list.add(file[i]);
            else{
                System.out.println(file[i].getAbsolutePath());
             }
        }
        File tmp;
        while (!list.isEmpty()) {
            tmp = (File)list.removeFirst();//首个目录
            if (tmp.isDirectory()) {
                file = tmp.listFiles();
                if (file == null)
                    continue;
                for (int i = 0; i < file.length; i++) {
                    if (file[i].isDirectory())
                        list.add(file[i]);//目录则加入目录列表，关键
                    else{
                        System.out.println(file[i]);

                    }
                }
            } else {
                System.out.println(tmp);
             }
        }
    }
    public static File[] getDirectoryListing(File dir) {
        //http://bunopus.blogspot.co.uk/2015/04/the-mystery-of-fitnesse-tests-order_19.html
        SortedSet<File> dirSet = new TreeSet<File>();
        SortedSet<File> fileSet = new TreeSet<File>();
        File[] files = dir.listFiles();
        if (files == null)
            return new File[0];
        for (int i = 0; i < files.length; i++) {
        }
        System.out.println(GsonJsonUtil.gson.toJson(files));
        return files;

    }


    static void displayDir(File dir, String prefix) {
        System.out.print(prefix);
        System.out.println(dir.getName());

        prefix = prefix.replace("├", "│");
        prefix = prefix.replace("└", " ");
        if (dir.isFile()) {
            return;
        }
        File files[] = dir.listFiles();

        for (int i = 0; files != null && i < files.length; i++) {
            if (i == files.length - 1) {
                displayDir(files[i], prefix + "└");
            } else {
                displayDir(files[i], prefix + "├");
            }
        }
    }

    static void addDir(String parent, String dir) {
        File file = new File(parent, dir);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdir();
        }
    }

    static void delDir(String parent, String dir) {
        File file = new File(parent, dir);
        if (file.exists() && file.isDirectory()) {
            file.delete();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        printTreeList(".");
    }


}
