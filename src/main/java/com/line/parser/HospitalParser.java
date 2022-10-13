package com.line.parser;

import com.line.domain.Hospital;

public class HospitalParser implements Parser<Hospital> {
    //csv파일에서 한 줄씩 받아서 객체로 만들어서 반환한다
    @Override
    public Hospital parse(String str) {
        //csv 파일을 받기 때문에
        String[] splitted = str.split(",");
        //id, address, category, emergencyRoom, name, subdivision
        Hospital hospital = new Hospital(splitted[0], splitted[1], splitted[2], Integer.parseInt(splitted[6]), splitted[10]);
        return hospital;
    }
}
