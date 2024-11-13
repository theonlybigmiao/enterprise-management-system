package com.zts.demo.entity;

public class Receipt {
    String meetingname;
    String unit;
    String name;
    String gender;
    String email;
    String phone;
    String roomType;
    String arrivalMethod;
    String arrivalFlight;
    String arrivalDate;
    String returnMethod;
    String returnFlight;
    String returnDate;
    String notes;

    public Receipt() {
    }

    public Receipt(String meetingname, String unit, String name, String gender, String email, String phone, String roomType, String arrivalMethod, String arrivalFlight, String arrivalDate, String returnMethod, String returnFlight, String returnDate, String notes) {
        this.meetingname = meetingname;
        this.unit = unit;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.roomType = roomType;
        this.arrivalMethod = arrivalMethod;
        this.arrivalFlight = arrivalFlight;
        this.arrivalDate = arrivalDate;
        this.returnMethod = returnMethod;
        this.returnFlight = returnFlight;
        this.returnDate = returnDate;
        this.notes = notes;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getArrivalMethod() {
        return arrivalMethod;
    }

    public void setArrivalMethod(String arrivalMethod) {
        this.arrivalMethod = arrivalMethod;
    }

    public String getArrivalFlight() {
        return arrivalFlight;
    }

    public void setArrivalFlight(String arrivalFlight) {
        this.arrivalFlight = arrivalFlight;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getReturnMethod() {
        return returnMethod;
    }

    public void setReturnMethod(String returnMethod) {
        this.returnMethod = returnMethod;
    }

    public String getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(String returnFlight) {
        this.returnFlight = returnFlight;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "meetingname='" + meetingname + '\'' +
                ", unit='" + unit + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roomType='" + roomType + '\'' +
                ", arrivalMethod='" + arrivalMethod + '\'' +
                ", arrivalFlight='" + arrivalFlight + '\'' +
                ", arrivalDate='" + arrivalDate + '\'' +
                ", returnMethod='" + returnMethod + '\'' +
                ", returnFlight='" + returnFlight + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
