package foodorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import foodorder.model.dto.StoreDTO;
import foodorder.model.util.DBUtil;

public class StoreDAO {
	private static Properties foodOrderSql = DBUtil.getSql();
	
	// 가게 추가
	public static boolean addStore(StoreDTO store) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(foodOrderSql.getProperty("addStore"));
			pstmt.setString(1, store.getStoreName());
			pstmt.setString(2, store.getStoreAddress());
			pstmt.setString(3, store.getStorePhone());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	// 가게 삭제
	public static boolean deleteStore(String storeName) throws SQLException, NumberFormatException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(foodOrderSql.getProperty("deleteStore"));
			pstmt.setString(1, storeName);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
		
	}

	// 특정 가게 이름으로정보 검색
	public static StoreDTO getStore(String storeName) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		StoreDTO store = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(foodOrderSql.getProperty("getStore"));
			pstmt.setString(1, storeName);
			rset = pstmt.executeQuery();
			if(rset.next()){
				store = new StoreDTO(rset.getString(1), rset.getString(2), rset.getString(3));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return store;
	}
	
	// 모든가게 정보 검색
	public static ArrayList<StoreDTO> getAllStore() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<StoreDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(foodOrderSql.getProperty("getAllStore"));
			rset = pstmt.executeQuery();
			
			list = new ArrayList<StoreDTO>();
			while(rset.next()){
				list.add(new StoreDTO(rset.getString(1), rset.getString(2), rset.getString(3)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	//가게 주소 변경
	public static boolean updateStore(String storeName, String address) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(foodOrderSql.getProperty("updateStore"));
			pstmt.setString(1, address);
			pstmt.setString(2, storeName);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}


	

	
}

