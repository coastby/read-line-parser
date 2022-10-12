package com.line.parser;

import com.line.domain.Hospital;

public class HospitalParser implements Parser<Hospital> {
    @Override
    public Hospital parse(String str) {
        //csv 파일을 받기 때문에
        String[] splitted = str.split(",");
        Hospital hospital = new Hospital(splitted[0]);

        return hospital;
    }
}
