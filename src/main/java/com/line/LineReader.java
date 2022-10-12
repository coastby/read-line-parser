package com.line;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LineReader {

    public List<String> readLines(String filename) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        while ((str = br.readLine()) != null) {
            result.add(str);
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        LineReader lr = new LineReader();
        String filename = "../data/서울시 병의원 위치 정보.csv";
        List<String> hospitals = lr.readLines(filename);
        System.out.println(hospitals.size());

    }
}
