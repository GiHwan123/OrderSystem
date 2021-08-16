package foodorder.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import foodorder.model.dto.MenuDTO;
import foodorder.model.util.DBUtil;
public class MenuDAO {
	
	private static Properties sql = DBUtil.getSql();
	
	// 메뉴 추가
	public static boolean addMenu(MenuDTO menu) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("addMenu"));
			pstmt.setString(1, menu.getMenuName());
			pstmt.setInt(2, menu.getMenuPrice());
			pstmt.setString(3, menu.getStoreName());
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 메뉴 수정(가격 수정)
	public static boolean updateMenu(String menuName, int menuPrice) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("updateMenu"));
			
			pstmt.setInt(1, menuPrice);
			pstmt.setString(2, menuName);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 메뉴 삭제
	public static boolean deleteMenu(String menuName) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("deleteMenu"));
			
			pstmt.setString(1, menuName);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 메뉴 단일 검색
	public static MenuDTO getMenu(String menuName) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MenuDTO menu = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getMenu"));
			
			pstmt.setString(1, menuName);
			rset = pstmt.executeQuery();
			if(rset.next()){
				menu = new MenuDTO(rset.getString(1), rset.getInt(2), rset.getString(3));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return menu;
	}
	
	// 특정 가게 메뉴 전체 검색
	public static ArrayList<MenuDTO> getStoreMenus(String storeName) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MenuDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getStoreMenus"));
			
			pstmt.setString(1, storeName);
			rset = pstmt.executeQuery();
			list = new ArrayList<MenuDTO>();
			while(rset.next()){
				list.add(new MenuDTO(rset.getString(1), rset.getInt(2), rset.getString(3)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	// 전 가게 메뉴 전체 검색
	public static ArrayList<MenuDTO> getAllMenus() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MenuDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getAllMenus"));
			rset = pstmt.executeQuery();
			list = new ArrayList<MenuDTO>();
			
			while(rset.next()){
				list.add(new MenuDTO(rset.getString(1), rset.getInt(2), rset.getString(3)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}