package com.snf.module.common.controller;

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
import com.snf.domain.CodeInfo;

@WebServlet("/common/code/insert")
public class CodeManageController extends HttpServlet{
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    	List<CodeInfo> codeList = new ArrayList<>();
	        request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");

	        String codeGroup = request.getParameter("codeGroup");
	        String codeGroupName = request.getParameter("codeGroupName");

	        // JDBC 연결 정보 설정
			String username = DBSettings.USERNAME;
			String password = DBSettings.PASSWORD;
			String databaseURL = DBSettings.DATABASE_URL;

	        try {
	            // JDBC 드라이버 로드
				Class.forName(DBSettings.CLASS_FOR_NAME);

	            // 데이터베이스 연결
	            Connection connection = DriverManager.getConnection(databaseURL, username, password);

	            // SQL 쿼리 실행 (여기서는 간단한 예제로 PreparedStatement 사용)
	            String query = "INSERT INTO CODE_GROUP (CODE_GROUP, CODE_GROUP_NAME, CREATED_DATE) VALUES (?, ?, NOW())";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, codeGroup);
	            preparedStatement.setString(2, codeGroupName);
	            preparedStatement.executeUpdate();

	            // 등록된 데이터 확인하기
	            String selectQuery = "SELECT row_number() over(order by ID asc) as num, ID, CODE_GROUP, CODE_GROUP_NAME, CREATED_DATE FROM CODE_GROUP";
	            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
	            ResultSet resultSet = selectStatement.executeQuery();
	            
	            CodeInfo coin = null;
	            while (resultSet.next()) {
	            	coin = new CodeInfo();
	            	coin.setNum(resultSet.getInt(1));
	            	coin.setId(resultSet.getInt(2));
	            	coin.setCodeGroup(resultSet.getString("CODE_GROUP"));
	            	coin.setCodeGroupName(resultSet.getString("CODE_GROUP_NAME"));
	            	coin.setCreatedDate(resultSet.getString("CREATED_DATE"));
	                codeList.add(coin);
	            }

	            // 리소스 정리
	            resultSet.close();
	            selectStatement.close();
	            preparedStatement.close();
	            connection.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        request.setAttribute("codeList", codeList);
	        request.getRequestDispatcher("/codeManage.jsp").forward(request, response);
	    }
}
