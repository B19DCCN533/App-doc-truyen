package com.example.appdoctruyen.model;

public class Truyen {
    private int ID;
    private String TenTruyen;
    private String NoiDung;
    private String Anh;
    private int TheLoai;
    private int LuotXem;
    private int ID_TK;

    public Truyen(String tenTruyen, String noiDung, String anh, int TheLoai, int LuotXem, int ID_TK) {
        TenTruyen = tenTruyen;
        NoiDung = noiDung;
        Anh = anh;
        this.TheLoai=TheLoai;
        this.LuotXem=LuotXem;
        this.ID_TK = ID_TK;
    }

    public Truyen(int ID, String tenTruyen, String noiDung, String anh,int TheLoai,int LuotXem, int ID_TK) {
        this.ID = ID;
        TenTruyen = tenTruyen;
        NoiDung = noiDung;
        Anh = anh;
        this.TheLoai=TheLoai;
        this.LuotXem=LuotXem;
        this.ID_TK = ID_TK;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenTruyen() {
        return TenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        TenTruyen = tenTruyen;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public int getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(int TheLoai) {
        this.TheLoai = TheLoai;
    }

    public int getLuotXem() {
        return LuotXem;
    }

    public void setLuotXem(int luotXem) {
        this.LuotXem = luotXem;
    }

    public int getID_TK() {
        return ID_TK;
    }

    public void setID_TK(int ID_TK) {
        this.ID_TK = ID_TK;
    }
}
