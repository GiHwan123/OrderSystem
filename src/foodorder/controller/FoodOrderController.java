package foodorder.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import foodorder.model.CustomerDAO;
import foodorder.model.MenuDAO;
import foodorder.model.OrdersDAO;
import foodorder.model.StoreDAO;
import foodorder.model.dto.CustomerDTO;
import foodorder.model.dto.MenuDTO;
import foodorder.model.dto.OrdersDTO;
import foodorder.model.dto.StoreDTO;
import foodorder.view.EndView;

public class FoodOrderController {
	
	private static Logger logger = Logger.getLogger("foodorder.controller.FoodOrderController");
	
	public static void orderAllView()  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String esc = null;
		
		try {
			while (esc == null) {
				System.out.println("===================����� ������ �����ϼ���.===================");
				System.out.println("1) ȸ�� \t 2) ���� \t 3) ������  0) �ý��� ����");

				int inputNum = Integer.parseInt(br.readLine());

				// ȸ��
				if (inputNum == 1) {

					System.out.println("\nȸ���̽ʴϱ�? Y/N:");
					String answer = br.readLine();

					if (answer.equals("N") || answer.equals("n")) {
						System.out.println("\n=====��ȣ�� �����ϼ���=====");
						System.out.println("1) ȸ������\t 2) ȸ����ȣ�� ��ȸ");
						int inputNum1 = Integer.parseInt(br.readLine());

						if (inputNum1 == 1) {
							System.out.println("\nȸ�������� ������ ������ ��Ŀ� �°� �Է��ϼ���. id/�̸�/�ڵ�����ȣ/�ּ�");
							System.out.println("\n");
							String[] nc = br.readLine().split("/");
							FoodOrderController.addCustomer(new CustomerDTO(nc[0], nc[1], nc[2], nc[3]));
							FoodOrderController.selectOne(nc[0]);
						} else if (inputNum1 == 2) {
							System.out.println("\nã�� ȸ���� id ���� �Է��ϼ���.");
							String inputId = br.readLine();
							FoodOrderController.selectOne(inputId);
						}

					} else if (answer.equals("Y") || answer.equals("y")) {
						System.out.println("\nid�� �Է����ּ���:");
						String id = br.readLine();

						if (CustomerDAO.selectOne(id) != null) {
							try {
								System.out.println("\n=====��ȣ�� �����ϼ���=====");
								System.out.println("1) ȸ����������\t 2) ȸ��Ż�� \t 3) ������ȸ \t 4) �޴���ȸ \t 5) �ֹ���ȸ \t 6) �ֹ��߰�");
								int inputNum1 = Integer.parseInt(br.readLine());
								if (inputNum1 == 1) {
									System.out.println("\n���� �� ���� ���� : 1(�̸�),2(�ڵ�����ȣ),3(�ּ�)");
									inputNum1 = Integer.parseInt(br.readLine());

									if (inputNum1 == 1) {
										System.out.println("\n�����Ͻ� �̸����� �ۼ����ּ���:");
										String name = (br.readLine());
										FoodOrderController.updateName(id, name);
										FoodOrderController.selectOne(id);
									} else if (inputNum1 == 2) {
										System.out.println("\n������ �ڵ��� ��ȣ�� �Է����ּ���:");
										String phoneNum = br.readLine();
										FoodOrderController.updatePhone(id, phoneNum);
										FoodOrderController.selectOne(id);
									} else if (inputNum1 == 3) {
										System.out.println("\n������ �ּҸ� ������ ��Ŀ� �°� �Է����ּ���:");
										String address = br.readLine();
										FoodOrderController.updateAddress(id, address);
										FoodOrderController.selectOne(id);
									}

								} else if (inputNum1 == 2) {
									System.out.println("\nŻ���� ȸ���� �̸��� �Է����ּ���: ");
									String name = br.readLine();
									FoodOrderController.deleteCustomer(id, name);

								} else if (inputNum1 == 3) {
									System.out.println("\n=====��ȣ�� �����ϼ���=====");
									System.out.println("1) ��簡�� ��ȸ\t 2) Ư������ ��ȸ ");
									int inputNum2 = Integer.parseInt(br.readLine());

									if (inputNum2 == 1) {
										FoodOrderController.getAllStore();
									} else if (inputNum2 == 2) {
										System.out.println("\n�˻��ϰ��� �ϴ� ���� �̸��� �Է����ּ���:");
										String storeName = br.readLine();
										FoodOrderController.getStore(storeName);
									}

								} else if (inputNum1 == 4) {
									System.out.println("\n=====��ȣ�� �����ϼ���=====");
									System.out.println("1) Ư�� ���� �޴� ��ȸ \t 2) Ư�� ���� �޴� ��ȸ ");
									int inputNum2 = Integer.parseInt(br.readLine());

									if (inputNum2 == 1) {
										System.out.println("\n�޴� ��ȸ�� ���ϴ� ���� �̸��� �Է��ϼ���:");
										String storeName = br.readLine();
										FoodOrderController.getStoreMenus(storeName);
									} else if (inputNum2 == 2) {
										System.out.println("\n�޴� �̸��� �Է��ϼ���:");
										String menuName = br.readLine();
										FoodOrderController.getMenu(menuName);
									}

								} else if (inputNum1 == 5) {
									System.out.println("\n=====��ȣ�� �����ϼ���=====");
									System.out.println("1) �� �ֹ� ��ȸ \t 2) ���ֹ� ���� \t 3) �� �ֹ� ���� ");
									int inputNum2 = Integer.parseInt(br.readLine());

									if (inputNum2 == 1) {
										FoodOrderController.getMyOrders(id);
									} else if (inputNum2 == 2) {
										System.out.println("\n�����ϰ����ϴ� �ֹ���ȣ�� �Է��ϼ���:");
										int order_no = Integer.parseInt(br.readLine());

										if (OrdersDAO.getOrders(order_no) != null ){
											System.out.println("\n���ϴ� ��������� �Է��ϼ���:");
											String method = br.readLine();

											FoodOrderController.updateOrders(order_no, method);
											FoodOrderController.getMyOrders(id);
										} else{
											System.out.println("�ش��ֹ���ȣ�� �������� �ʽ��ϴ�. �ٽ� Ȯ���� �ּ���.");
										}
									} else if (inputNum2 == 3) {
										System.out.println("\n�����ϰ��� �ϴ� ��ȣ�� �Է��ϼ���:");
										int order_no = Integer.parseInt(br.readLine());
										FoodOrderController.deleteOrders(order_no);
									}

								} else if (inputNum1 == 6) {
									System.out.println("\n�߰��� �ֹ� ������ ������ ��Ŀ� �°� �Է��ϼ���. �����̸�/�޴��̸�/������� \n");
									String[] orderInfo = br.readLine().split("/");
									FoodOrderController
											.addOrders(new OrdersDTO(id, orderInfo[0], orderInfo[1], orderInfo[2]));
								}

							} catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
//								e.printStackTrace();
								EndView.showError("���信 �°� �Է����ּ���.");
							}
						}

						else if (CustomerDAO.selectOne(id) == null) {
							System.out.println("id�� Ȯ�����ּ���!!");
						}

					}
				}
				// ����
				if (inputNum == 2) {
					System.out.println("\n���� �̸��� �Է����ּ���.");
					String storeName = br.readLine();

					System.out.println("\n=====��ȣ�� �����ϼ���=====");
					System.out.println("1) ���� �ֹ� ��ü ��ȸ\t 2) ���� �޴� ����(�߰�/����/����) \t 3) ���� �ּ� ����");
					int inputNum1 = Integer.parseInt(br.readLine());

					if (inputNum1 == 1) {
						FoodOrderController.getStoreOrders(storeName);

					} else if (inputNum1 == 2) {
						System.out.println("\n=====��ȣ�� �����ϼ���=====");
						System.out.println("1) ���ο� �޴�\t 2) �޴� ���� ���� \t 3) ���� �޴� ����");
						int inputNum2 = Integer.parseInt(br.readLine());

						if (inputNum2 == 1) {
							System.out.println("\n�߰��� �޴� ������ ������ ��Ŀ� �°� �Է��ϼ���. �޴��̸�/����\n");
							String[] menuInfo = br.readLine().split("/");
							FoodOrderController
									.addMenu(new MenuDTO(menuInfo[0], Integer.parseInt(menuInfo[1]), storeName));
							FoodOrderController.getStoreMenus(storeName);
						} else if (inputNum2 == 2) {
							System.out.println("\n���� ������ �޴� ������ ������ ��Ŀ� �°� �Է��ϼ���. �޴��̸�/����\n");
							String[] menuInfo = br.readLine().split("/");
							FoodOrderController.updateMenu(menuInfo[0], Integer.parseInt(menuInfo[1]));
							FoodOrderController.getStoreMenus(storeName);
						} else if (inputNum2 == 3) {
							System.out.println("\n������ �޴� �̸��� �Է��ϼ���:\n");
							String menuName = br.readLine();
							FoodOrderController.deleteMenu(menuName);
							FoodOrderController.getStoreMenus(storeName);
						}

					} else if (inputNum1 == 3) {
						System.out.println("\n����� �ּҸ� �Է��ϼ���:\n");
						String address = br.readLine();
						FoodOrderController.updateStore(storeName, address);
						FoodOrderController.getStore(storeName);
					}

				}

				// ������
				else if (inputNum == 3) {
					System.out.println("**********������ �α���**********");
					System.out.println("\n=====����� ��� ��ȣ�� �����ϼ���=====");
					System.out.println("1) ȸ������ ��ü ��ȸ \t 2) ȸ������ ���� \t 3) ȸ������ ���� \t 4) Ư�� ȸ�� ���� ��ȸ \n"
							+ "5) ��� ���� ���� ��ȸ \t 6) Ư�� ���� ���� ��ȸ \t 7) Ư�� ���� ���� ���� \t 8) Ư�� ���� ���� �߰� \n"
							+ "9) �����ϴ� ��ü �޴� ��ȸ \t 10) Ư�� ���� �޴� ��ü ��ȸ \t 11) Ư�� �޴� ���� ��ȸ \n"
							+ "12) ��ü �ֹ� ��ȸ \t 13) Ư�� ���� �ֹ� ��ü ��ȸ \t 14)Ư�� �ֹ� ���� ��ȸ");
					int inputNum1 = Integer.parseInt(br.readLine());
					switch (inputNum1) {
					case 1:
						FoodOrderController.selectAll();
						break;
					case 2:
						System.out.println("\n���� �� ���� ���� : 1(�̸�),2(�ڵ�����ȣ),3(�ּ�)");
						inputNum1 = Integer.parseInt(br.readLine());
						if (inputNum1 == 1) {
							System.out.println("\n������ ȸ�� id�� ������ �̸��� ������ ��Ŀ� �°� �Է��ϼ���. id/�̸�");
							String[] nc = (br.readLine()).split("/");
							FoodOrderController.updateName(nc[0], nc[1]);
							FoodOrderController.selectOne(nc[0]);
						} else if (inputNum1 == 2) {
							System.out.println("\n������ ȸ�� id�� ������ �ڵ��� ��ȣ�� ������ ��Ŀ� �°� �Է��ϼ���. id/�ڵ�����ȣ");
							String[] nc = (br.readLine()).split("/");
							FoodOrderController.updatePhone(nc[0], nc[1]);
							FoodOrderController.selectOne(nc[0]);
						} else if (inputNum1 == 3) {
							System.out.println("\n������ ȸ�� id�� ������ �ּҸ� ������ ��Ŀ� �°� �Է��ϼ���. id/�ּ�");
							String[] nc = (br.readLine()).split("/");
							FoodOrderController.updateAddress(nc[0], nc[1]);
							FoodOrderController.selectOne(nc[0]);
						}
						break;
					case 3:
						System.out.println("\nŻ���� ȸ���� id�� �̸��� ������ ��Ŀ� �°� �Է��ϼ���. id/�̸�");
						System.out.println("\n");
						String[] nc = (br.readLine()).split("/");
						FoodOrderController.deleteCustomer(nc[0], nc[1]);
						break;
					case 4:
						System.out.println("\n��ȸ�� ȸ�� id �Է��ϼ���:");
						String id = br.readLine();
						FoodOrderController.selectOne(id);
						break;
					case 5:
						System.out.println(
								"=============================================��簡�� ����=============================================");
						FoodOrderController.getAllStore();
						break;
					case 6:
						System.out.println("\n�˻��� ���� �̸��� �Է��ϼ���:");
						FoodOrderController.getStore(br.readLine());
						break;
					case 7:
						System.out.println("\n������ ���� �̸��� �Է��ϼ���:");
						String storeName = br.readLine();
						FoodOrderController.deleteStore(storeName);
						FoodOrderController.getAllStore();
						break;
					case 8:
						System.out.println("\n������ ��Ŀ� ���� �ۼ����ּ���. �����̸�/������ġ/����ó \n");
						String[] storeInfo = br.readLine().split("/");
						FoodOrderController.insertStore(new StoreDTO(storeInfo[0], storeInfo[1], storeInfo[2]));
						FoodOrderController.getAllStore();
						break;
					case 9:
						System.out.println("\n================��ü �޴� ���=================");
						FoodOrderController.getAllMenus();
						break;
					case 10:
						System.out.println("\n�޴��� �˻��� ���� �̸��� �Է��ϼ���.");
						String findStore = br.readLine();
						FoodOrderController.getStoreMenus(findStore);
						break;
					case 11:
						System.out.println("\n�˻��� �޴��� �̸��� �Է��ϼ���.");
						FoodOrderController.getMenu(br.readLine());
						break;
					case 12:
						System.out.println("\n==========������ ��ü �ֹ� ��ȸ============");
						FoodOrderController.getAllOrders();
						break;
					case 13:
						System.out.println("\n�ֹ��� Ȯ���� ���� �̸��� �Է��ϼ���.");
						FoodOrderController.getStoreOrders(br.readLine());
						break;
					case 14:
						System.out.println("\nȮ���� �ֹ� ��ȣ�� �Է����ּ���.");
						FoodOrderController.getOrders(Integer.parseInt(br.readLine()));
						break;
					}

				} else if (inputNum == 0) {
					System.out.println("�ý��� ����");
					break;

				}
			}
		} catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException | SQLException e) {
			e.printStackTrace();
			EndView.showError("���信 �°� �Է����ּ���.");

		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Customer - CRUD
	 */
	
	// ȸ�� ����
	public static void addCustomer(CustomerDTO customer) {
		boolean result = false;
		String function = "����";

		try {
			result = CustomerDAO.addCustomer(customer);
			if (result) {
				logger.trace("[id]" + customer.getId() + "[name]" + customer.getName() + ": ȸ�� ���� ����");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<ȸ������ ���� �߻�>>>");
			logger.error("[id]" + customer.getId() + "[name]" + customer.getName() + " <<<ȸ�� ���� ���� �߻�>>>");
		}finally {
			EndView.showMessage(result, customer.getName(), function);
		}
	}

	// ȸ�� Ż��
	public static void deleteCustomer(String id, String name) {
		boolean result = false;
		String function = "Ż��";

		try {
			result = CustomerDAO.deleteCustomer(id, name);
			if (result) {
				logger.trace("[name]" + name + ": ȸ�� Ż�� ���� ����");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<ȸ�� Ż�� ����>>");
			EndView.showError("�ܿ� �ֹ� ������ �����ϴ��� Ȯ�����ּ���.");
			logger.error("[name]" + name + " <<<ȸ�� Ż�� ����>>");
		}finally {
			EndView.showMessage(result, id, function);
		}
	}

	// ȸ�� ���� ����(�̸�)
	public static void updateName(String id, String name) {
		boolean result = false;
		String function = "�̸� ����";

		try {
			result = CustomerDAO.updateName(id, name);
			if (result) {
				logger.trace("[id]" + id + "[name]" + name + ": �̸� ���� ����");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<�̸� ���� ����>>>");
			logger.error("[id]" + id + "[name]" + name + " <<<�̸� ���� ����>>>");
		}finally {
			EndView.showMessage(result, id, function);
		}
	}

	// ȸ�� ���� ����(��ȭ��ȣ)
	public static void updatePhone(String id, String phone) {
		boolean result = false;
		String function = "��ȭ��ȣ ����";

		try {
			result = CustomerDAO.updatePhone(id, phone);
			if (result) {
				logger.trace("[id]" + id + "[phone]" + phone + ": ��ȭ��ȣ ���� ����");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<��ȭ��ȣ ���� ����>>>");
			logger.error("[id]" + id + "[phone]" + phone + " <<<��ȭ��ȣ ���� ����>>>");
		}finally {
			EndView.showMessage(result, id, function);
		}
	}

	// ȸ�� ���� ����(�ּ�)
	public static void updateAddress(String id, String address) {
		boolean result = false;
		String function = "�ּ� ����";

		try {
			result = CustomerDAO.updateAddress(id, address);
			if (result) {
				logger.trace("[id]" + id + "[address]" + address + ": �ּ� ���� ����");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<�ּ� ���� ����>>>");
			logger.error("[id]" + id + "[address]" + address + " <<<�ּ� ���� ����>>>");
		}finally {
			EndView.showMessage(result, id, function);
		}
	}

	// ȸ�� ���� ��ü ��ȸ
	public static void selectAll() {
		ArrayList<CustomerDTO> all = null;

		try {
			all = CustomerDAO.selectAll();
			if (all.size() != 0) {
				EndView.printAll(all);
				logger.trace("��� ȸ�� ���� ��ȸ �õ�");
			}
			else {
				System.out.println("�����ϴ� ȸ���� �����ϴ�.");
				logger.trace("��� ȸ�� ���� ��ȸ �õ�");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<ȸ�� ���� ��ü ��ȸ ����>>>");
			logger.error("<<<ȸ�� ���� ��ü ��ȸ ����>>>");
		}
	}

	// ȸ�� ���� ���� ��ȸ
	public static void selectOne(String id) {
		CustomerDTO one = null;

		try {
			one = CustomerDAO.selectOne(id);
			if (one != null) {
				EndView.printOne(one);
				logger.trace("[id]" + id + ": ȸ�� ���� ��ȸ �õ�");
			}
			else {
				System.out.println("�Է� ������ ��ġ�ϴ� ȸ���� �����ϴ�.");
				logger.trace("[id]" + id + ": �������� �ʴ� ȸ�� ���� ���� ��ȸ �õ�");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<ȸ�� ���� ���� ��ȸ ����>>>");
			logger.error("[id]" + id + " <<<ȸ�� ���� ���� ��ȸ ����>>>");
		}
	}
	
	
	/**
	 * Menu - CRUD
	 */
	
	// ���ο� �޴� �߰�
	public static void addMenu(MenuDTO menu) {
		String function = "�޴� �߰�";
		boolean result = false;
	
		try{
			result = MenuDAO.addMenu(menu);
			if(result) {
				logger.trace("[menuName]" + menu.getMenuName() + ": �޴� �߰� ����");
			}
		}catch(SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<�޴� ���� ����>>>");
			logger.error("[menu]" + menu.getMenuName() + " <<<�޴� ���� ����>>>");
		}finally {
			EndView.showMessage(result, menu.getMenuName(), function);
		}
	}
	
	// ���� �޴� ����(����)
	public static void updateMenu(String menuName, int menuPrice) {
		String function = "���� ����";
		boolean result = false;
		
		try{
			result = MenuDAO.updateMenu(menuName, menuPrice);
			if(result) {
				logger.trace("[menuName]" + menuName + "[menuPrice]" + menuPrice + ": �޴� �߰� ����");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<�޴� ���� ���� ����>>>");
			logger.error("[menuName]" + menuName + "[menuPrice]" + menuPrice + " <<<�޴� ���� ���� ����>>>");
			EndView.showMessage(result, menuName, function);
		}
	}
	
	// ���� �޴� ����
	public static void deleteMenu(String menuName) {
		String function = "�޴� ����";
		boolean result = false;
		
		try{
			result = MenuDAO.deleteMenu(menuName);
			if(result) {
				logger.trace("[menuName]" + menuName + ": �޴� ���� ����");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<�Ŵ� ���� ����>>>");
			logger.error("[menuName]" + menuName + ": <<<�Ŵ� ���� ����>>>");
		}finally {
			EndView.showMessage(result, menuName, function);
		}
	}
	
	
	// Ư�� �޴� ���� ��ȸ
	public static void getMenu(String menuName) {
		MenuDTO one = null;
		
		try {
			one = MenuDAO.getMenu(menuName);
			if(one != null) {
				EndView.printOne(one);
				logger.trace("[menuName]" + menuName + ": �޴� ���� ��ȸ �õ�");
			}else {
				System.out.println("�Է� ������ ��ġ�ϴ� �޴��� �����ϴ�.");
				logger.trace("[menuName]" + menuName + ": �������� �ʴ� �޴� ���� ���� ��ȸ �õ�");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<�޴� ���� ���� ��ȸ ����>>>");
			logger.error("[menuName]" + menuName + " <<<�޴� ���� ���� ��ȸ ����>>>");
		}
	}
	
	// Ư�� ���� �޴� ���� ��ȸ
	public static void getStoreMenus(String storeName) {
		ArrayList<MenuDTO> all = null;
		
		try{
			all = MenuDAO.getStoreMenus(storeName);
			if(all.size() != 0) {
				EndView.printAll(MenuDAO.getStoreMenus(storeName));
				logger.trace("[storeName]" + storeName + ": ���� �޴� ���� ��ȸ �õ�");
			}else {
				System.out.println("�Է� ������ ��ġ�ϴ� �޴��� �����ϴ�.");
				logger.trace("[storeName]" + storeName + ": �������� �ʴ� �޴� ���� ��ü ��ȸ �õ�");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<Ư�� ���� �޴� ���� ���� ��ȸ ����>>");
			logger.error("[storeName]" + storeName + ": <<Ư�� ���� �޴� ���� ���� ��ȸ ����>>");
		}
	}
	
	// ��ü �޴� ���� ��ȸ
	public static void getAllMenus(){
		ArrayList<MenuDTO> all = null;
		
		try{
			all = MenuDAO.getAllMenus();
			if(all.size() != 0) {
				EndView.printAll(MenuDAO.getAllMenus());
				logger.trace("��� �޴� ���� ��ȸ �õ�");
			}else {
				System.out.println("�����ϴ� �޴��� �����ϴ�.");
				logger.trace("��� �޴� ���� ��ȸ �õ�");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<�޴� ���� ��ü ��ȸ ����>>>");
			logger.error("<<<�޴� ���� ��ü ��ȸ ����>>>");
		}
	}
	
	
	/**
	 * Orders - CRUD
	 */
	
	// ���ο� �ֹ� �߰�(��� ��� �ٸ�)
	public static void addOrders(OrdersDTO order) {
		try{
			boolean result = OrdersDAO.addOrders(order);
			if(result) {
				logger.trace("[order_no]" + order.getOrder_no() + ": �ֹ� �߰� ����");
			}
		}catch(SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<�ֹ� �߰� ����>>>");
			logger.error("[order_no]" + order.getOrder_no() + " <<<�ֹ� �߰� ����>>>");
		}
		
	}

	// ���� �ֹ� ����(���� ���)
	public static void updateOrders(int order_no, String payMethod) {
		String function = "���� ��� ����";
		boolean result = false;

		try{
			result = OrdersDAO.updateOrders(order_no, payMethod);
			if(result) {
				logger.trace("[order_no]" + order_no + ": ���� ��� ���� ����");
			}
		}catch(Exception e){
//			e.printStackTrace();
			EndView.showError("<<<���� ��� ���� ����>>>");
			logger.error("[order_no]" + order_no + " <<<���� ��� ���� ����>>>");
		}finally {
			EndView.showMessage(result, order_no, function);
		}
	}
	
	// ���� �ֹ� ����
	public static void deleteOrders(int order_no) {
		String function = "�ֹ� ����";
		boolean result = false;
		
		try{
			result = OrdersDAO.deleteOrders(order_no);
			if(result) {
				logger.trace("[order_no]" + order_no + ": �ֹ� ���� ����");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<�ֹ� ���� ����>>>");
			logger.error("[order_no]" + order_no + " <<<�ֹ� ���� ����>>>");
		}finally {
			EndView.showMessage(result, order_no, function);
		}
	}
	
	// Ư�� �ֹ� ���� ��ȸ
	public static void getOrders(int order_no) {
		OrdersDTO one = null;
		
		try {
			one = OrdersDAO.getOrders(order_no);
			if(one != null) {
				EndView.printOne(one);
				logger.trace("[order_no]" + order_no + ": �ֹ� ���� ��ȸ �õ�");
			}else {
				System.out.println("�Է� ������ ��ġ�ϴ� �ֹ��� �����ϴ�.");
				logger.trace("[order_no]" + order_no + ": �������� �ʴ� �ֹ� ���� ��ü ��ȸ �õ�");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<�ֹ� ���� ���� ��ȸ ����>>>");
			logger.error("[order_no]" + order_no + " <<<�ֹ� ���� ���� ��ȸ ����>>>");
		}
	}
	
	// Ư�� ���� �ֹ� ���� ��ȸ
	public static void getStoreOrders(String storeName) {
		ArrayList<OrdersDTO> all = null;
		
		try{
			all = OrdersDAO.getStoreOrders(storeName);
			if(all.size() != 0) {
				EndView.printAll(all);
				logger.trace("[storeName]" + storeName + ": �ֹ� ���� ��ü ��ȸ �õ�");	
			}else {
				System.out.println("�Է� ������ ��ġ�ϴ� �ֹ��� �����ϴ�.");
				logger.trace("[storeName]" + storeName + ": �������� �ʴ� �ֹ� ���� ��ü ��ȸ �õ�");
			}
		}catch (SQLException e){
//			e.printStackTrace();
			EndView.showError("<<Ư�� ���� �ֹ� ���� ���� ��ȸ ����>>");
			logger.error("[storeName]" + storeName + ": <<Ư�� ���� �ֹ� ���� ���� ��ȸ ����>>");
		}
	}
	
	// Ư�� ȸ�� �ֹ� ���� ��ȸ
		public static void getMyOrders(String id) {
			ArrayList<OrdersDTO> all = null;
			
			try{
				all = OrdersDAO.getMyOrders(id);
				if(all.size() != 0) {
					EndView.printAll(all);	
					logger.trace("[id]" + id + ": ȸ�� �ֹ� ���� ��ü ��ȸ �õ�");
				}else {
					System.out.println("�Է� ������ ��ġ�ϴ� �ֹ��� �����ϴ�.");
					logger.trace("[id]" + id + ": �������� �ʴ� �ֹ� ���� ��ü ��ȸ �õ�");
				}
			}catch (SQLException e){
//				e.printStackTrace();
				EndView.showError("<<Ư�� ȸ�� �ֹ� ���� ���� ��ȸ ����>>");
				logger.error("[id]" + id + " <<Ư�� ȸ�� �ֹ� ���� ���� ��ȸ ����>>");
			}
		}
	
	// ��ü �ֹ� ���� ��ȸ
	public static void getAllOrders(){
		ArrayList<OrdersDTO> all = null;
		
		try{
			all = OrdersDAO.getAllOrders();
			if(all.size() != 0) {
				EndView.printAll(all);
				logger.trace("��� �ֹ� ���� ��ȸ �õ�");	
			}else {
				System.out.println("�����ϴ� �ֹ��� �����ϴ�.");
				logger.trace("��� �ֹ� ���� ��ȸ �õ�");
			}
		}catch (SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<�ֹ� ���� ��ü ��ȸ ����>>>");
			logger.error("<<<�ֹ� ���� ��ü ��ȸ ����>>>");
		}
	}
	
	/**
	 * Store - CRUD
	 */
	
	// ��ü ���� ���� ��ȸ
	public static void getAllStore(){
		ArrayList<StoreDTO> all = null;
		
		try {
			all = StoreDAO.getAllStore();
			if(all.size() != 0) {
				EndView.printAll(all);
				logger.trace("��� ���� ���� ��ȸ �õ�");
			}else {
				System.out.println("�����ϴ� ���԰� �����ϴ�.");
				logger.trace("��� ���� ���� ��ȸ �õ�");
			}
		}catch(SQLException s) {
//			s.printStackTrace();
			EndView.showError("<<<���� ���� ��ü ��ȸ ����>>>");
			logger.error("<<<���� ���� ��ü ��ȸ ����>>>");
		}
	}
	
	// ���� ���� ���� ��ȸ
	public static void getStore(String storeName) {
		StoreDTO one = null;
		
		try {
			one = StoreDAO.getStore(storeName);
			if(one != null) {
				EndView.printOne(one); 
				logger.trace("[storeName]" + storeName + ": ���� ���� ��ȸ �õ�");
			}else {
				System.out.println("�Է� ������ ��ġ�ϴ� ���԰� �����ϴ�.");
				logger.trace("[storeName]" + storeName + ": �������� �ʴ� �޴� ���� ���� ��ȸ �õ�");
			}			 
		}catch(SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<���� ���� ���� ��ȸ ����>>>");
			logger.error("[storeName]" + storeName + ": <<<���� ���� ���� ��ȸ ����>>>");
		}
	}
	
	// ���� ���� ����(�ּ�)
	public static void updateStore(String storeName, String address) {
		boolean result = false;
		String function = "�ּ� ����";
		
		try {
			result = StoreDAO.updateStore(storeName, address);
			if(result) {
				logger.trace("[storeName]" + storeName + "[address]" + address + ": �ּ� ���� ����");
			}
		}catch(SQLException s) {
//			s.printStackTrace();
			EndView.showError("<<<�ּ� ���� ����>>>");
			logger.error("[storeName]" + storeName + "[address]" + address + " <<<�ּ� ���� ����>>>");
		}finally {
			EndView.showMessage(result, storeName, function);
		}
	}
	
	// ���� �߰� 
	public static void insertStore(StoreDTO store)  {
		boolean result = false;
		String function = "���� �߰�";
		
		try {
			result = StoreDAO.addStore(store);
			if(result) {
				logger.trace("[storeName]" + store.getStoreName() + ": ���� �߰� ����");
			}
		}catch(SQLException e) {
//			s.printStackTrace();
			EndView.showError("<<<���� ���� ����>>>");
			logger.error("[storeName]" + store.getStoreName() + " <<<���� ���� ����>>>");
		}finally {
			EndView.showMessage(result, store.getStoreName(), function);
		}
	}
	
	// ���� ����
	public static void deleteStore(String storeName) {
		String function = "���� ����";
		boolean result = false;
		
		try {
			if(result) {
			result = StoreDAO.deleteStore(storeName);
			logger.trace("[storeName]" + storeName + ": ���� ���� ����");
			}
		}catch(SQLException | NumberFormatException s){
//			s.printStackTrace();
			EndView.showError("<<<���� ���� ����>>");
			EndView.showError("�ܿ� �ֹ� ������ �����ϴ��� Ȯ�����ּ���.");
			logger.error("[storeName]" + storeName + " <<<���� ���� ����>>");
		}
		finally {
			EndView.showMessage(result, storeName, function);
		}
		
	}
	
}
