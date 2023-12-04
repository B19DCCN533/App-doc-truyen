package com.example.appdoctruyen.model;

public class Taikhoan {
    private int mId;
    private String mTenTaikhoan;
    private String mMatkhau;
    private String mEmail;
    private int mPhanQuyen;

    public Taikhoan(String mTenTaikhoan, String mMatkhau, String mEmail, int mPhanQuyen){
        this.mTenTaikhoan=mTenTaikhoan;
        this.mMatkhau=mMatkhau;
        this.mEmail=mEmail;
        this.mPhanQuyen=mPhanQuyen;
    }

    public Taikhoan(String mTenTaikhoan, String mEmail) {
        this.mTenTaikhoan = mTenTaikhoan;
        this.mEmail = mEmail;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTenTaikhoan() {
        return mTenTaikhoan;
    }

    public void setmTenTaikhoan(String mTenTaikhoan) {
        this.mTenTaikhoan = mTenTaikhoan;
    }

    public String getmMatkhau() {
        return mMatkhau;
    }

    public void setmMatkhau(String mMatkhau) {
        this.mMatkhau = mMatkhau;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public int getmPhanQuyen() {
        return mPhanQuyen;
    }

    public void setmPhanQuyen(int mPhanQuyen) {
        this.mPhanQuyen = mPhanQuyen;
    }
}
