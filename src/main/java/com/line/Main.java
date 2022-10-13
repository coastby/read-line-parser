package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LineReader<Hospital> hospitalLineReader = new LineReader<>(new HospitalParser());
        String filename = "../data/서울시병의원위치11.csv";

        List<Hospital> hospitals = hospitalLineReader.readLines(filename);
        List<String> lines = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            String line = hospital.getAllForSql();
            lines.add(line);
        }
        System.out.println(hospitals.size());

        //파일에 쓰는 코드 : 덮어써지니 돌리기 전 확인
//        File file = new File("./src/main/java/com/line/seoul_hospital_insert.sql");
//        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//        for (String line : lines) {
//            writer.write(line);
//        }
//        writer.close();
    }
}
