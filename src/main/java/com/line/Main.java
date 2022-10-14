package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LineReader<Hospital> hospitalLineReader = new LineReader<>(new HospitalParser());
        String filename = "../data/서울시 병의원 위치 정보.csv";

        List<Hospital> hospitals = hospitalLineReader.readLines(filename);
        List<String> lines = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            String line = hospital.getAllForSql();
            lines.add(line);
        }

        LineWriter lineWriter = new LineWriter();
        String sqlFilename = "./src/main/java/com/line/seoul_hospital_insert.sql";
        lineWriter.writeLines(sqlFilename, lines);


    }
}
