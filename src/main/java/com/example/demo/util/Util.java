package com.example.demo.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {
    private static Random random = new Random();

    public static List<String> getImageUrls() {
        List<String> imageUrl = new ArrayList<>();
        int size = random.nextInt(2) + 3;
        for (int i = 0; i < size; i++) {
            int nextInt = random.nextInt(14) + 1;
            imageUrl.add(nextInt + ".jpg");
        }
        return imageUrl;
    }

    public static String getGameUrl() {
        return "game" + random.nextInt(10) + ".jpg";
    }
}
