package com.snf.module.material.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.snf.domain.MaterialData;

@WebServlet("/material/insert")
public class MaterialController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static List<MaterialData> materialList = new ArrayList<>();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String largeCat = request.getParameter("largeCat");
        String midCat = request.getParameter("midCat");
        String smallCat = request.getParameter("smallCat");

        // JDBC 연결 정보 설정
        String username = "root";
        String password = "0000";
        String databaseURL = "jdbc:mysql://localhost:3306/invMng";

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.jdbc.Driver");

            // 데이터베이스 연결
            Connection connection = DriverManager.getConnection(databaseURL, username, password);

            // SQL 쿼리 실행 (여기서는 간단한 예제로 PreparedStatement 사용)
            String query = "INSERT INTO MI01 (LC, MC, SC) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, largeCat);
            preparedStatement.setString(2, midCat);
            preparedStatement.setString(3, smallCat);
            preparedStatement.executeUpdate();

            // 등록된 데이터 확인하기
            String selectQuery = "SELECT * FROM MI01";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
            	int id = resultSet.getInt(1);
                String lc = resultSet.getString("LC");
                String mc = resultSet.getString("MC");
                String sc = resultSet.getString("SC");
                materialList.add(new MaterialData(id, lc, mc, sc));
                System.out.println("id : " + id + " , 대분류: " + lc + ", 중분류: " + mc + ", 소분류: " + sc);
            }

            // 리소스 정리
            resultSet.close();
            selectStatement.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("materialList", materialList);
        request.getRequestDispatcher("/createMaterial.jsp").forward(request, response);
        //response.sendRedirect("/createMaterial.jsp");
    }
}
