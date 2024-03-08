package com.ohgiraffers.section01.model.dto;

import java.awt.*;

public class MenuDTO {

    // 중요! 1. 오른쪽 데이터베이스에서 끌어올 목록들 private으로 만들어주기!
    private int menu_code;
    private String menu_name;
    private int menu_price;
    private int category_code;
    private String orderable_status;


    // 중요! 2. 4가지(기본생성자(1),(4), getter and setter, toString
//    public MenuDTO(){               // 기본 생성자_& 모두 자료형 기본으로 설정되어 있다 ▼

    public MenuDTO(int menu_code, String menu_name, int menu_price, int category_code, String orderable_status) {
        this.menu_code = menu_code;
        this.menu_name = menu_name;
        this.menu_price = menu_price;
        this.category_code = category_code;
        this.orderable_status = orderable_status;
    }

    public int getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(int menu_code) {
        this.menu_code = menu_code;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public int getMenu_price() {
        return menu_price;
    }

    public void setMenu_price(int menu_price) {
        this.menu_price = menu_price;
    }

    public int getCategory_code() {
        return category_code;
    }

    public void setCategory_code(int category_code) {
        this.category_code = category_code;
    }

    public String getOrderable_status() {
        return orderable_status;
    }

    public void setOrderable_status(String orderable_status) {
        this.orderable_status = orderable_status;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menu_code=" + menu_code +
                ", menu_name='" + menu_name + '\'' +
                ", menu_price=" + menu_price +
                ", category_code=" + category_code +
                ", orderable_status='" + orderable_status + '\'' +
                '}';
    }

}