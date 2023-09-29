package com.snf.module.history;

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

import com.snf.database.DBSettings;
import com.snf.domain.MaterialData;

@WebServlet("/material/historycheck")
public class HistoryController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    	String fromDate = request.getParameter("fromDate");
    	String toDate = request.getParameter("toDate");
    	
    	 List<MaterialData> materialList = new ArrayList<>();

 		// JDBC 연결 정보 설정
 		String username = DBSettings.USERNAME;
 		String password = DBSettings.PASSWORD;
 		String databaseURL = DBSettings.DATABASE_URL;

 		try {
 			// JDBC 드라이버 로드
 			Class.forName(DBSettings.CLASS_FOR_NAME);

             // 데이터베이스 연결
             Connection connection = DriverManager.getConnection(databaseURL, username, password);

             // 등록된 데이터 확인하기
             String selectQuery = "SELECT row_number() over(order by M2.LC asc) as num, M2.LC , M2.MC , M2.SC , M.INOUT_TYPE , M.INOUT_QTY , M.INOUT_DATE, M.OUT_NAME FROM MI03 M JOIN MI01 M2 ON M.M_ID = M2.ID WHERE INOUT_DATE >= ? and INOUT_DATE <= ?";
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
             preparedStatement.setString(1, fromDate);
             preparedStatement.setString(2, toDate);
             
             ResultSet resultSet = preparedStatement.executeQuery();

             while (resultSet.next()) {
                 int num = resultSet.getInt(1);
                 String lc = resultSet.getString("LC");
                 String mc = resultSet.getString("MC");
                 String sc = resultSet.getString("SC");
                 String iotyp = resultSet.getString("INOUT_TYPE");
                 String ioqty = resultSet.getString("INOUT_QTY");
                 String iodate = resultSet.getString("INOUT_DATE");
                 String outName = resultSet.getString("OUT_NAME");
                 materialList.add(new MaterialData(num, lc, mc, sc, iotyp, iodate, ioqty, outName));
                 System.out.println("num : " + num + ", 대분류: " + lc + ", 중분류: " + mc + ", 소분류: " + sc + "  타입:  " + iotyp + "  수량 : " + ioqty + "  날짜  : " + iodate);
             }
             // 리소스 정리
             resultSet.close();
             preparedStatement.close();
             connection.close();
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
    	

        request.setAttribute("materialList", materialList);
        request.getRequestDispatcher("/historyCheck.jsp").forward(request, response);
    }
}
