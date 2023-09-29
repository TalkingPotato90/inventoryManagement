package com.snf.module.unstoring;

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

@WebServlet("/material/unstoring")
public class UnstoringController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<MaterialData> materialList = new ArrayList<>();
        List<MaterialData> materialSelectlist = new ArrayList<>();
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String largeCat = request.getParameter("largeCat");
        String midCat = request.getParameter("midCat");
        String smallCat = request.getParameter("smallCat");
        String ioTyp = request.getParameter("ioTyp");
        String ioQty = request.getParameter("ioQty");
        String ioDate = request.getParameter("ioDate");
        String outName = request.getParameter("outName");
        
        // JDBC 연결 정보 설정
		String username = DBSettings.USERNAME;
		String password = DBSettings.PASSWORD;
		String databaseURL = DBSettings.DATABASE_URL;

        try {
            // JDBC 드라이버 로드
			Class.forName(DBSettings.CLASS_FOR_NAME);

            // 데이터베이스 연결
            Connection connection = DriverManager.getConnection(databaseURL, username, password);

            //id를 조회 해오는 쿼리
            String query = "SELECT ID from MI01 A where A.LC= ? and A.MC = ? AND A.SC = ?";
            PreparedStatement selectStatement = connection.prepareStatement(query);
            selectStatement.setString(1, largeCat);
            selectStatement.setString(2, midCat);
            selectStatement.setString(3, smallCat);
            
            ResultSet resultSet = selectStatement.executeQuery();

            int id = 0;
            while (resultSet.next()) {
            	id = resultSet.getInt(1);
            }
            
            // SQL 쿼리 실행 (여기서는 간단한 예제로 PreparedStatement 사용)
            query = "INSERT INTO MI03 (M_ID, INOUT_TYPE, INOUT_QTY, INOUT_DATE, OUT_NAME) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, ioTyp);
            preparedStatement.setString(3, ioQty);
            preparedStatement.setString(4, ioDate);
            preparedStatement.setString(5, outName);
            preparedStatement.executeUpdate();
            
            // SQL 쿼리 실행 (재고 수량 입고 처리)
            query = "SELECT QTY from MI02 WHERE M_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            int curQty = 0;
            while (resultSet.next()) {
            	curQty = resultSet.getInt(1);
            }
            
            int difQty = curQty - Integer.parseInt(ioQty);
            
            query = "UPDATE MI02 SET QTY = ? WHERE M_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, difQty);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            
            String selectQuery = "SELECT row_number() over(order by LC asc) as num, ID, LC, MC, SC FROM MI01";
            selectStatement = connection.prepareStatement(selectQuery);
            resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
            	int num = resultSet.getInt(1);
            	int id2 = resultSet.getInt(2);
                String lc = resultSet.getString("LC");
                String mc = resultSet.getString("MC");
                String sc = resultSet.getString("SC");
                materialSelectlist.add(new MaterialData(id2, lc, mc, sc));
            }
            
            // 리소스 정리
            preparedStatement.close();
            connection.close();

            // 등록된 데이터를 조회하여 저장
            materialList = getMaterialDataFromDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("materialSelectlist", materialSelectlist);
        request.setAttribute("materialList", materialList);
        request.getRequestDispatcher("/unstoring.jsp").forward(request, response);
    }

    private List<MaterialData> getMaterialDataFromDatabase() {
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
            String selectQuery = "SELECT row_number() over(order by M2.LC asc) as num, M2.LC , M2.MC , M2.SC , M.INOUT_TYPE , M.INOUT_QTY , M.INOUT_DATE, M.OUT_NAME FROM MI03 M JOIN MI01 M2 ON M.M_ID = M2.ID WHERE M.INOUT_TYPE = '출고'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
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

        return materialList;
    }
}
