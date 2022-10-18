package com.line.parser;

import com.line.domain.Hospital;

public class HospitalParser implements Parser<Hospital> {
    //병원 이름을 받아서 세부과를 반환한다.
    private String getSubdivision(String name) {
        String[] subdivisions = {"의원", "병원", "한방병원",  "치과", "외과", "성형외과", "내과", "소아청소년과", "가정의학과", "피부과", "영상의학과", "정형외과", "흉부외과", "신경외과","산부인과", "재활의학과", "비뇨기과", "안과", "소아과", "정신건강의학과", "요양병원", "통증의학과", "이비인후과", "한의원"};
        String subdivision = null;
        for (String s : subdivisions) {
            if (name.contains(s)) {
                subdivision = s;
            }
        }
        //정형외과 키워드 추가???
//        if (name.contains("마디")){
//            subdivision = "정형외과";
//        }
        return subdivision;
    }

    //csv파일에서 한 줄씩 받아서 객체로 만들어서 반환한다
    @Override
    public Hospital parse(String str) {
        //"(쌍따옴표)가 있는 파일이라면 아래 코드 사용, 현재 csv 파일에서는 없어서 사용 안 함
//        str = str.replace("\"","");

        //csv 파일을 받기 때문에
        String[] splitted = str.split(",");
        String subdivision = getSubdivision(splitted[10]);

        //id, address, category, emergencyRoom, name, subdivision
        Hospital hospital = new Hospital(splitted[0], splitted[1], splitted[2], Integer.parseInt(splitted[6]), splitted[10], subdivision);
        return hospital;
    }
}
