package com.ohgiraffers.section01.problem;

import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemOfUsingDirectSQLTests { // SQL을 직접 썼을때 사용하는 문제들

    private Connection con;

    @BeforeEach
    void setConnection() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/menudb";
        String user = "swcamp";
        String password = "swcamp";

        Class.forName(driver);

        con = DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        con.rollback();
        con.close();
    }

    @DisplayName("직접 SQL을 작성하여 메뉴를 조회할 때 발생하는 문제 확인 ( 그동안 JDBC하면서 얼마나 귀찮았는지 )")
    @Test
    void testDirectSelectSql() throws SQLException {

        // given
        String query = "SELECT MENU_CODE, MENU_NAME, MENU_PRICE, CATEGORY_CODE,"
                        + "ORDERABLE_STATUS FROM TBL_MENU";

        // when
        Statement stmt = con.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        List<Menu> menuList = new ArrayList<>();
        while (rset.next()) {
            Menu menu = new Menu();
            menu.setMenuCode(rset.getInt("MENU_CODE"));
            menu.setMenuName(rset.getString("MENU_NAME"));
            menu.setMenuPrice(rset.getInt("MENU_PRICE"));
            menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
            menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS"));

            menuList.add(menu);
        }

        // then
        Assertions.assertTrue(!menuList.isEmpty());
        menuList.forEach(System.out::println);

        rset.close();
        stmt.close();

    }
}
