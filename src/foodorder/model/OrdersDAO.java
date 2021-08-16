package foodorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import foodorder.model.dto.OrdersDTO;
import foodorder.model.util.DBUtil;

public class OrdersDAO {
	
	private static Properties sql = DBUtil.getSql();
	
	// �ֹ� �߰�
	public static boolean addOrders(OrdersDTO order) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int order_no;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("addOrders"));
			pstmt.setString(1, order.getId());
			pstmt.setString(2, order.getStoreName());
			pstmt.setString(3, order.getMenuName());
			pstmt.setString(4, order.getPayMethod());

			int result = pstmt.executeUpdate();
			if(result == 1){
				pstmt = con.prepareStatement(sql.getProperty("register2"));
				rset = pstmt.executeQuery();
				if (rset.next()) {
					order_no = rset.getInt("currval");
					System.out.println("�ֹ�����! �ֹ���ȣ : " + order_no);
				}
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt,rset);
		}
		return false;
	}
	
	// �ֹ� ����(���� ���)
	public static boolean updateOrders(int order_no, String payMethod) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("updateOrders"));
			
			pstmt.setString(1, payMethod);
			pstmt.setInt(2, order_no);

			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	// �ֹ� ����
	public static boolean deleteOrders(int order_no) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("deleteOrders"));
			
			pstmt.setInt(1, order_no);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	// �ֹ� ���� �˻�
	public static OrdersDTO getOrders(int order_no) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrdersDTO order = null;

		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getOrders"));
			
			pstmt.setInt(1, order_no);
			rset = pstmt.executeQuery();
			if(rset.next()){
				order = new OrdersDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return order;
	}
	
	// Ư�� ���� �ֹ� ��ü �˻�
	public static ArrayList<OrdersDTO> getStoreOrders(String storeName) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrdersDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getStoreOrders"));
			
			pstmt.setString(1, storeName);
			rset = pstmt.executeQuery();

			list = new ArrayList<OrdersDTO>();
			while(rset.next()){
				list.add(new OrdersDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	// ��id�� �ֹ� �˻�
	public static ArrayList<OrdersDTO> getMyOrders(String id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrdersDTO> list = null;
		
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getMyOrders"));
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			list = new ArrayList<OrdersDTO>();
			while(rset.next()){
				list.add(new OrdersDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	// �� ���� �޴� ��ü �˻�
	public static ArrayList<OrdersDTO> getAllOrders() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrdersDTO> list = null;

		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql.getProperty("getAllOrders"));
			rset = pstmt.executeQuery();
			list = new ArrayList<OrdersDTO>();
			
			while(rset.next()){
				list.add(new OrdersDTO(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
}
