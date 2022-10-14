package com.line;

import java.io.*;
import java.util.List;

public class LineWriter {

    public void createFile(String filename) throws IOException {
        File file = new File(filename);
        file.createNewFile();
    }


//  파일이 저장될 경로와 String 리스트를 매개변수로 주면 파일에 쓰기를 해주는 메서드
//  사용 예시)
//  SqlWriter sqlWriter = new SqlWriter();
//  String sqlFilename = "./src/main/java/com/line/seoul_hospital_insert.sql";
//  sqlWriter.writeSqlLines(sqlFilename, lines);
    public void writeLines(String filename, List<String> lines) throws IOException {
        File file = new File(filename);
        //OutputStreamWriter : 인코딩을 위한
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        try {
            for (String line : lines) {
                writer.write(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        writer.close();
    }
}
