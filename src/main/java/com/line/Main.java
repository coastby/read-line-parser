package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        LineReader<Hospital> hospitalLineReader = new LineReader<>(new HospitalParser());
        String filename = "../data/서울시병의원위치11.csv";
        List<Hospital> hospitals = hospitalLineReader.readLines(filename);

        for (Hospital hospital : hospitals) {
            String line = hospital.getAllForSql();
            System.out.println(line);
        }
    }
}
