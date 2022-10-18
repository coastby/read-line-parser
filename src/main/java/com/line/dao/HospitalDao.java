package com.line.dao;

import com.line.LineReader;
import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalDao {
    //csv filename 입력하면 sql로 저장
    public void add(String filename) throws SQLException, ClassNotFoundException, IOException {
        ConnectionMaker cm = new ConnectionMaker();
        LineReader lr = new LineReader(new HospitalParser());
        Connection conn = cm.makeConnection();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO seoul_hospital(id, address, district, category, emergency_room, name, subdivision)"
                +"VALUES (?, ?, ?, ?, ?, ?, ?);");
        List<Hospital> hospitals= lr.readLines(filename);
        for (Hospital hospital : hospitals) {
            ps.setString(1, hospital.getId());
            ps.setString(2, hospital.getAddress());
            ps.setString(3, hospital.getDistrict());
            ps.setString(4, hospital.getCategory());
            ps.setInt(5, hospital.getEmergencyRoom());
            ps.setString(6, hospital.getName());
            ps.setString(7, hospital.getSubdivision());

        }


        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * 병원이름을 파라미터로 넘겨주면 데이터에서 해당되는 Hospital 객체 리스트를 반환하는 메서드
     */
    public List<Hospital> findByName(String name) throws SQLException, ClassNotFoundException {
        Connection conn = new ConnectionMaker().makeConnection();

        PreparedStatement ps = conn.prepareStatement("SELECT id, address, category, emergency_room, name, subdivision FROM seoul_hospital WHERE name=?");
        ps.setString(1, name);

        ResultSet rs = ps.executeQuery();
        List<Hospital> hospitals = new ArrayList<>();
        while(rs.next()){
            Hospital hospital = new Hospital(rs.getString("id"), rs.getString("address"), rs.getString("category"), rs.getInt("emergency_room"), rs.getString("name"));
            hospitals.add(hospital);
        }

        System.out.println("SELECT 완료");
        rs.close();
        ps.close();
        conn.close();

        return hospitals;

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HospitalDao hd = new HospitalDao();
        List<Hospital> hs = hd.findByName("삼성서울병원");
        for (Hospital h : hs) {
            System.out.println(h.getAddress());
        }
    }


}
