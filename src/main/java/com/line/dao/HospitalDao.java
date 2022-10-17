package com.line.dao;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospitalDao {
    //미완성
    public void add(List<Hospital> hospitals) throws SQLException, ClassNotFoundException {
        ConnectionMaker cm = new ConnectionMaker();
        Connection conn = cm.makeConnection();

        PreparedStatement ps = conn.prepareStatement("INSERT INTO seoul_hospital(id, address, district, category, emergency_room, name, subdivision)"
                +"VALUES (?, ?, ?, ?, ?, ?, ?);");

        ps.setString(1, "");
        ps.setString(2, "");
        ps.setString(3, "");
        ps.setString(4, "");
        ps.setInt(5, 0);
        ps.setString(6, "");
        ps.setString(7, "");

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    /**
     * 병원이름을 파라미터로 넘겨주면 데이터에서 해당되는 Hospital 객체 리스트를 반환하는 메서드
     * @param name
     * @return List<Hospital></Hospital>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Hospital> getByName(String name) throws SQLException, ClassNotFoundException {
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
        List<Hospital> hs = hd.getByName("삼성서울병원");
        for (Hospital h : hs) {
            System.out.println(h.getAddress());
        }
    }


}
