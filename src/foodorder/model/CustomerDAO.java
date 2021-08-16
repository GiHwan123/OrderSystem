package foodorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import foodorder.model.dto.CustomerDTO;
import foodorder.model.util.DBUtil;

public class CustomerDAO {

	private static Properties sql = DBUtil.getSql();

	// 고객 추가
	public static boolean addCustomer(CustomerDTO customer) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("addCustomer"));
			pstmt.setString(1, customer.getId());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getPhone());
			pstmt.setString(4, customer.getAddress());
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		}finally {
			DBUtil.close(con, pstmt,rset);
		}
		return false;
	}

	// 고객 삭제
	public static boolean deleteCustomer(String id, String name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("deleteCustomer"));
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			} 
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 고객 정보 수정(이름)
	public static boolean updateName(String id, String name) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("updateName"));
			pstmt.setString(1, name);
			pstmt.setString(2, id);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 고객 정보 수정(전화번호)
	public static boolean updatePhone(String id, String phone) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("updatePhone"));
			pstmt.setString(1, phone);
			pstmt.setString(2, id);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			} 
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 고객 정보 수정(주소)
	public static boolean updateAddress(String id, String address) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("updateAddress"));
			pstmt.setString(1, address);
			pstmt.setString(2, id);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			} 
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 고객 정보 전체 검색
	public static ArrayList<CustomerDTO> selectAll() throws SQLException {
		ArrayList<CustomerDTO> all = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("selectAll"));
			rset = pstmt.executeQuery();
			
			all = new ArrayList<>();
			
			while (rset.next()) {
				all.add(new CustomerDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4)));
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return all;
	}

	// 특정 고객 정보 단일 검색
	public static CustomerDTO selectOne(String id) throws SQLException {
		CustomerDTO one = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("selectOne"));
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				one = new CustomerDTO(rset.getString(1), rset.getString(2), rset.getString(3),
						rset.getString(4));
			} 
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return one;

	}
}
