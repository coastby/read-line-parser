package com.line;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class javaPrac {
    public static void main(String[] args) throws IOException {
        String name = "건강한가정의학과의원";
        String[] subdivisions = {"한의원", "한방병원", "병원", "치과", "성형외과", "내과", "소아청소년과", "가정의학과", "피부과", "영상의학과", "정형외과", "흉부외과", "신경외과","산부인과", "재활의학과", "비뇨기과", "안과", "소아과", "정신건강의학과", "요양병원", ""};
        String subdivision = null;
        for (String s : subdivisions) {
            if (name.contains(s)) {
                subdivision = s;
            }
        }
        System.out.println(subdivision);

        String name2 = "건강한가정의학과의원";
        String str = "가정의학과";
        String subdivision2 = null;
            if (name.contains(str)) {
                subdivision = str;
            }

        System.out.println(subdivision2);
    }
}
