package com.line.parser;

import com.line.domain.Hospital;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HospitalParserTest {
    String line1 = "A1120837,서울특별시 금천구 벚꽃로 286 삼성리더스타워 111~114호 (가산동),C,의원,G099,응급의료기관 이외,2,외과: 상시진료 내과는 당분간 휴진,서울시 송파구 문정동 장지동 법조단지 위례 가락동 가락시장역 위치 삼성서울병원 외래교수 출신 구강외과 전문의 진료 진료과목 - 임플란트 치조골 뼈이식 수술 매복 사랑니 발치 턱관절 악관절 질환의 치료 교정 치료 및 기타 보존 보철(크라운 브릿지 인레이) 신경치료,방이역 1번출구 바로옆 굿모닝 신한증권 뒷건물,가산기대찬의원,02-6267-2580,02-920-5374,1930,1930,1930,1930,1930,1500,1500,1500,0900,0900,0900,0900,0900,0900,1000,1000,085,11,126.88412249700781,37.4803938036867,2022-04-07 14:55:00.0";
    HospitalParser hospitalParser = new HospitalParser();

    @Test
    @DisplayName("id, 주소 잘 가져오는지 test")
    void getAddressTest() {
        Hospital hospital = hospitalParser.parse(line1);
        Assertions.assertEquals("A1120837", hospital.getId());
        Assertions.assertEquals("서울특별시 금천구 벚꽃로 286 삼성리더스타워 111~114호 (가산동)", hospital.getAddress());

    }

    @Test
    @DisplayName("sql query를 잘 만드는지 test")
    void makeSqlQueryTest () {
        Hospital hospital = hospitalParser.parse(line1);
        String sql = hospital.getAllForSql();
        String result = "('A1120837', '서울특별시 금천구 벚꽃로 286 삼성리더스타워 111~114호 (가산동)', '서울특별시 금천구', 'C', '2', '가산기대찬의원', '의원'),\n";
        Assertions.assertEquals(result, sql);
    }


}