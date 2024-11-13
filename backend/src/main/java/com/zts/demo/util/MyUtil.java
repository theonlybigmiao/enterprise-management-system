package com.zts.demo.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MyUtil {
    public static String getCurrentTime(){
        LocalDateTime now = LocalDateTime.now();

        // 定义MySQL datetime格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前日期和时间为MySQL datetime格式的字符串
        return now.format(formatter);
    }
    public static String generateRenterKey() {
        // 定义可能的字符集合
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        // 创建一个Random实例来生成随机数
        Random random = new Random();
        StringBuilder sb = new StringBuilder("RE"); // 初始字符串"RE"

        // 生成5位随机小写字母并追加到StringBuilder
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        // 将StringBuilder转换为String并返回
        return sb.toString();
    }
    public static String generateCeUserKey() {
        // 定义可能的字符集合
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        // 创建一个Random实例来生成随机数
        Random random = new Random();
        StringBuilder sb = new StringBuilder("CE"); // 初始字符串"RE"

        // 生成5位随机小写字母并追加到StringBuilder
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        // 将StringBuilder转换为String并返回
        return sb.toString();
    }

    public static String generateDeUserKey() {
        // 定义可能的字符集合
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        // 创建一个Random实例来生成随机数
        Random random = new Random();
        StringBuilder sb = new StringBuilder("DE"); // 初始字符串"RE"

        // 生成5位随机小写字母并追加到StringBuilder
        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        // 将StringBuilder转换为String并返回
        return sb.toString();
    }

}
