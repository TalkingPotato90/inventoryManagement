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

import com.snf.database.DBSettings;
import com.snf.domain.MaterialData;

@WebServlet("/material/update")
public class MaterialUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<MaterialData> materialList = new ArrayList<>();
		request.setCharacterEncoding(DBSettings.ENCODING);
		response.setCharacterEncoding(DBSettings.ENCODING);

		String paramId = request.getParameter("id"); 
		String largeCat = request.getParameter("lc"); 
		String midCat = request.getParameter("mc"); 
		String smallCat = request.getParameter("sc");

		int resultCount = 0;

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
			String query = "UPDATE MI01 SET LC = ?, MC = ?, SC = ? WHERE ID = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, largeCat);
			preparedStatement.setString(2, midCat);
			preparedStatement.setString(3, smallCat);
			preparedStatement.setString(4, paramId);
			resultCount = preparedStatement.executeUpdate();

			// 등록된 데이터 확인하기
			String selectQuery = "SELECT row_number() over(order by LC asc) as num, ID, LC, MC, SC FROM MI01";
			PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = selectStatement.executeQuery();

			while (resultSet.next()) {
				int num = resultSet.getInt(1);
				int id = resultSet.getInt(2);
				String lc = resultSet.getString("LC");
				String mc = resultSet.getString("MC");
				String sc = resultSet.getString("SC");
				materialList.add(new MaterialData(num, id, lc, mc, sc));
				System.out.println("num : " + num + ", 대분류: " + lc + ", 중분류: " + mc + ", 소분류: " + sc);
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
	}
}
