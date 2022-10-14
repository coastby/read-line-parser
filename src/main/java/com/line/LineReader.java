package com.line;

import com.line.parser.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LineReader<T> {

    Parser<T> parser;
    boolean isRemoveFirstLine = true;
    public LineReader(Parser<T> parser) {
        this.parser = parser;
    }

    public List<String> readLinesBefore(String filename) throws IOException {
        List<String> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        while ((str = br.readLine()) != null) {
            result.add(str);
        }
        return result;
    }

    public List<T> readLines(String filename) throws IOException {
        List<T> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String str;
        //첫 번째줄 버림 : default는 true로 되어있다.
        if (isRemoveFirstLine) {
            br.readLine();
        }
        while ((str = br.readLine()) != null) {
            result.add(parser.parse(str));
        }
        return result;
    }

}
