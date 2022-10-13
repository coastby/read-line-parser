package com.line.parser;

import com.line.domain.Hospital;

public class HospitalParser implements Parser<Hospital> {
    //csv파일에서 한 줄씩 받아서 객체로 만들어서 반환한다
    @Override
    public Hospital parse(String str) {
        //"(쌍따옴표)가 있는 파일이라면 아래 코드 사용, 현재 csv 파일에서는 없어서 사용 안 함
//        str = str.replace("\"","");
        //csv 파일을 받기 때문에
        String[] splitted = str.split(",");
        //id, address, category, emergencyRoom, name, subdivision
        Hospital hospital = new Hospital(splitted[0], splitted[1], splitted[2], Integer.parseInt(splitted[6]), splitted[10]);
        return hospital;
    }
}
