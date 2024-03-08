package com.ohgiraffers.section01.model.dao;

import com.ohgiraffers.section01.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBC.template.close;

/* 필기.
 *   DAO(DataBase Access Object)
 *   데이터 베이스 접근용 객체
 *   => CRUD 연산을 담당하는 메소드들의 집합으로 이루어진 클래스
 *  */
public class MenuDAO {

    // 1. query 문을 읽어야 됨
    private Properties prop = new Properties();
    // ▲ Properties 룰 읽을 수 있음 ▲

    public MenuDAO() {

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);

            // 바로 읽을 수 있게 미리 세팅이 되어있음. ???
        }
    }

    // #(1) Application 에서 create method 해서 만들어짐
    public int selectLastMenuCode(Connection con) {
//        Connection con = getConnection();     //할 필요없다. 이미 app에서 연결되어있으니깐
        Statement stmt = null;
        ResultSet rset = null;

        int maxMenuCode = 0;          // 밑에 (1),(2)와 같지만, 어떻게 담을지만이 다르다.

        String query = prop.getProperty("selectLastMenuCode");  // (* key 값 복사해서 커리문 작성!) 1번째 커리만들기~

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);           // 드디어 쿼리문 실행!(rset에 넣어줄거임!)

            if(rset.next()) {  // 최대값 1개만 구하면 되므로, 반복문이 아닌, 'IF 문' 으로 바로 실행
                maxMenuCode = rset.getInt("MAX(MENU_CODE)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            close(rset);
            close(stmt);
        }

        return maxMenuCode;         // void가 아닌 자료형 int로 메소드 만들어서 return!!!
    }


    // #(2) Application 에서 create method 해서 만들어짐
    public List<Map<Integer, String>> selectAllCategory(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        List<Map<Integer, String>> categoryList = null;

        String query = prop.getProperty("selectAllCategoryList"); // 2번째 쿼리만들기~

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            categoryList = new ArrayList<>();

            while (rset.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));

                categoryList.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {

            close(rset);
            close(stmt);
        }

        return categoryList;

    }

    public int insertNewMenu(Connection con, MenuDTO newMenu) {

        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("insertMenu");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, newMenu.getMenu_code());
            pstmt.setString(2, newMenu.getMenu_name());
            pstmt.setInt(3, newMenu.getMenu_price());
            pstmt.setInt(4, newMenu.getCategory_code());
            pstmt.setString(5, newMenu.getOrderable_status());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            close(pstmt);

        }

        return result;

    }
}