package com.stanstoynov;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileLockChecker {

    private static boolean isFileLocked() {
        try {
            RandomAccessFile randomFile = new RandomAccessFile("src/resources/LOCK","rw");
            FileChannel channel = randomFile.getChannel();
            if(channel.tryLock() == null) {
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void checkForOtherInstance() {
        if(FileLockChecker.isFileLocked()) {
            System.out.println("APP ALREADY RUNNING!");
            System.exit(0);
        }

    }



}
