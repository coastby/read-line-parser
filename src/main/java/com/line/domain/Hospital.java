package com.line.domain;

public class Hospital {
    //일단 id만 저장함
    private String id;
    private String address;
    private String district;
    private String category;
    private Integer emergencyRoom;
    private String name;
    private String subdivision;

    public Hospital(String id, String address, String category, Integer emergencyRoom, String name) {
        this.id = id;
        this.address = address;
        this.district = district;
        this.category = category;
        this.emergencyRoom = emergencyRoom;
        this.name = name;
        this.subdivision = subdivision;
        this.setDistrict();
        this.setSubdivision();
    }

    private void setDistrict() {
        String[] splitted = address.split(" ");
        String district = splitted[0]+" "+splitted[1];
        this.district = district;
    }

    private void setSubdivision() {
        String[] subdivisions = {"의원", "병원", "한방병원",  "치과", "외과", "성형외과", "내과", "소아청소년과", "가정의학과", "피부과", "영상의학과", "정형외과", "흉부외과", "신경외과","산부인과", "재활의학과", "비뇨기과", "안과", "소아과", "정신건강의학과", "요양병원", "통증의학과", "이비인후과", "한의원"};
        String subdivision = null;
        for (String s : subdivisions) {
            if (name.contains(s)) {
                subdivision = s;
            }
        }
        this.subdivision = subdivision;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getCategory() {
        return category;
    }

    public Integer getEmergencyRoom() {
        return emergencyRoom;
    }

    public String getName() {
        return name;
    }

    public String getSubdivision() {
        return subdivision;
    }
    public String getAllForSql() {
        String result = "('"+this.id+"', '"+this.address+"', '"+this.district+"', '"+this.category+"', '"+this.emergencyRoom+"', '"+this.name+"', '"+this.subdivision+"'),\n";
        return result;
    }
}
