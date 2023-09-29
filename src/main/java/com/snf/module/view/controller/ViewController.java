package com.snf.module.view.controller;

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

@WebServlet("/view/viewPage")
public class ViewController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	 
    	String path = request.getParameter("pageName");
    	
    	if(path.equals("createMaterial")) {
    		moveCreateMaterialPage(request, response);
    	}else if(path.equals("storing")) {
    		moveStoringPage(request, response);
    	}else if(path.equals("unstoring")) {
    		moveUnstoringPage(request, response);
    	}else if(path.equals("inventoryCheck")) {
    		moveInventoryCheckPage(request, response);
    	}else if(path.equals("historyCheck")) {
    		moveHistoryCheckPage(request, response);
    	}else if(path.equals("codeManage")) {
    		moveCodeManagePage(request, response);
    	}
    }
    
    /** 코드 관리 페이지 */
    public void moveCodeManagePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String selectQuery = "SELECT row_number() over(order by M2.LC asc) as num, M2.LC , M2.MC , M2.SC , M.INOUT_TYPE , M.INOUT_QTY , M.INOUT_DATE, M.OUT_NAME FROM MI03 M JOIN MI01 M2 ON M.M_ID = M2.ID";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

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
                //constructor MaterialData(int, String, String, String, String, String, String) is undefined
                System.out.println("num : " + num + ", 대분류: " + lc + ", 중분류: " + mc + ", 소분류: " + sc + "  타입:  " + iotyp + "  수량 : " + ioqty + "  날짜  : " + iodate);
                // num : 1, 대분류: 메인보드, 중분류: ,
            }
            
            // 리소스 정리
            resultSet.close();
            selectStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

       request.setAttribute("materialList", materialList);
    	
    	request.getRequestDispatcher("/codeManage.jsp").forward(request, response);
	}



	// 자재 이력 조회 페이지 이동
    private void moveHistoryCheckPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String selectQuery = "SELECT row_number() over(order by M2.LC asc) as num, M2.LC , M2.MC , M2.SC , M.INOUT_TYPE , M.INOUT_QTY , M.INOUT_DATE, M.OUT_NAME FROM MI03 M JOIN MI01 M2 ON M.M_ID = M2.ID";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

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
                //constructor MaterialData(int, String, String, String, String, String, String) is undefined
                System.out.println("num : " + num + ", 대분류: " + lc + ", 중분류: " + mc + ", 소분류: " + sc + "  타입:  " + iotyp + "  수량 : " + ioqty + "  날짜  : " + iodate);
                // num : 1, 대분류: 메인보드, 중분류: ,
            }
            
            // 리소스 정리
            resultSet.close();
            selectStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

       request.setAttribute("materialList", materialList);
    	
    	request.getRequestDispatcher("/historyCheck.jsp").forward(request, response);
	}

    // 현재 재고 조회 페이지 이동
	private void moveInventoryCheckPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            String selectQuery = "SELECT row_number() over(order by A.LC asc) as num, A.LC, A.MC, A.SC, B.QTY FROM MI01 A INNER JOIN MI02 B ON A.ID = B.M_ID";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

            MaterialData m = null;
            while (resultSet.next()) {
            	m = new MaterialData();
            	m.setNum(resultSet.getInt(1));
                m.setLargeCat(resultSet.getString("LC"));
                m.setMidCat(resultSet.getString("MC"));
                m.setSmallCat(resultSet.getString("SC"));
                m.setQty(resultSet.getString("QTY"));
                materialList.add(m);
            }
            
            // 리소스 정리
            resultSet.close();
            selectStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

       request.setAttribute("materialList", materialList);
       request.getRequestDispatcher("/inventoryCheck.jsp").forward(request, response);
	}


	// 자재 출고 페이지 이동
	private void moveUnstoringPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MaterialData> materialList = new ArrayList<>();
   	    List<MaterialData> materialSelectlist = new ArrayList<>();

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
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();

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
                //constructor MaterialData(int, String, String, String, String, String, String) is undefined
                System.out.println("num : " + num + ", 대분류: " + lc + ", 중분류: " + mc + ", 소분류: " + sc + "  타입:  " + iotyp + "  수량 : " + ioqty + "  날짜  : " + iodate);
                // num : 1, 대분류: 메인보드, 중분류: ,
            }

            selectQuery = "SELECT row_number() over(order by LC asc) as num, ID, LC, MC, SC FROM MI01";
            selectStatement = connection.prepareStatement(selectQuery);
            resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
            	int num = resultSet.getInt(1);
            	int id = resultSet.getInt(2);
                String lc = resultSet.getString("LC");
                String mc = resultSet.getString("MC");
                String sc = resultSet.getString("SC");
                materialSelectlist.add(new MaterialData(id, lc, mc, sc));
            }
            
            // 리소스 정리
            resultSet.close();
            selectStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

       request.setAttribute("materialList", materialList);
       request.setAttribute("materialSelectlist", materialSelectlist);
		
		request.getRequestDispatcher("/unstoring.jsp").forward(request, response);
	}


	// 자재 입고 페이지 이동
	private void moveStoringPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	    List<MaterialData> materialList = new ArrayList<>();
	   	    List<MaterialData> materialSelectlist = new ArrayList<>();

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
	            String selectQuery = "SELECT row_number() over(order by M2.LC asc) as num, M2.LC , M2.MC , M2.SC , M.INOUT_TYPE , M.INOUT_QTY , M.INOUT_DATE FROM MI03 M JOIN MI01 M2 ON M.M_ID = M2.ID WHERE M.INOUT_TYPE = '입고'";
	            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
	            ResultSet resultSet = selectStatement.executeQuery();

	            while (resultSet.next()) {
	                int num = resultSet.getInt(1);
	                String lc = resultSet.getString("LC");
	                String mc = resultSet.getString("MC");
	                String sc = resultSet.getString("SC");
	                String iotyp = resultSet.getString("INOUT_TYPE");
	                String ioqty = resultSet.getString("INOUT_QTY");
	                String iodate = resultSet.getString("INOUT_DATE");
	                materialList.add(new MaterialData(num, lc, mc, sc, iotyp, iodate, ioqty));
	                //constructor MaterialData(int, String, String, String, String, String, String) is undefined
	                System.out.println("num : " + num + ", 대분류: " + lc + ", 중분류: " + mc + ", 소분류: " + sc + "  타입:  " + iotyp + "  수량 : " + ioqty + "  날짜  : " + iodate);
	                // num : 1, 대분류: 메인보드, 중분류: ,
	            }

	            selectQuery = "SELECT row_number() over(order by LC asc) as num, ID, LC, MC, SC FROM MI01";
	            selectStatement = connection.prepareStatement(selectQuery);
	            resultSet = selectStatement.executeQuery();

	            while (resultSet.next()) {
	            	int num = resultSet.getInt(1);
	            	int id = resultSet.getInt(2);
	                String lc = resultSet.getString("LC");
	                String mc = resultSet.getString("MC");
	                String sc = resultSet.getString("SC");
	                materialSelectlist.add(new MaterialData(id, lc, mc, sc));
	            }
	            
	            // 리소스 정리
	            resultSet.close();
	            selectStatement.close();
	            connection.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }

	       request.setAttribute("materialList", materialList);
	       request.setAttribute("materialSelectlist", materialSelectlist);
		request.getRequestDispatcher("/storing.jsp").forward(request, response);
	}

	// 자재등록 페이지 이동
    private void moveCreateMaterialPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            }

            // 리소스 정리
            resultSet.close();
            selectStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

       request.setAttribute("materialList", materialList);
       request.getRequestDispatcher("/createMaterial.jsp").forward(request, response);
	}
}
