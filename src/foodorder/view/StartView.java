package foodorder.view;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import foodorder.controller.FoodOrderController;

public class StartView {

	static Logger logger = Logger.getLogger("foodorder.view.StartView");

	public static void main(String[] args) throws SQLException{
		FoodOrderController.orderAllView();
	}	

}			


