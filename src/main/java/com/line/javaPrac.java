package com.line;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class javaPrac {
    public static void main(String[] args) throws IOException {
        File file = new File("./src/main/java/com/line/seoul_hospital_insert.sql");
        file.createNewFile();
    }
}
