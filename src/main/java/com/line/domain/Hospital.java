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

    public Hospital(String id, String address, String category, Integer emergencyRoom, String name, String subdivision) {
        this.id = id;
        this.address = address;
        this.category = category;
        this.emergencyRoom = emergencyRoom;
        this.name = name;
        this.subdivision = subdivision;
        this.setDistrict();
    }

    private void setDistrict() {
        String[] splitted = address.split(" ");
        String district = splitted[0]+" "+splitted[1];
        this.district = district;
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
