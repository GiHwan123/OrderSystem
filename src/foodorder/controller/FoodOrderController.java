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
				System.out.println("===================사용자 유형을 선택하세요.===================");
				System.out.println("1) 회원 \t 2) 점주 \t 3) 관리자  0) 시스템 끄기");

				int inputNum = Integer.parseInt(br.readLine());

				// 회원
				if (inputNum == 1) {

					System.out.println("\n회원이십니까? Y/N:");
					String answer = br.readLine();

					if (answer.equals("N") || answer.equals("n")) {
						System.out.println("\n=====번호를 선택하세요=====");
						System.out.println("1) 회원가입\t 2) 회원번호로 조회");
						int inputNum1 = Integer.parseInt(br.readLine());

						if (inputNum1 == 1) {
							System.out.println("\n회원가입할 정보를 오른쪽 양식에 맞게 입력하세요. id/이름/핸드폰번호/주소");
							System.out.println("\n");
							String[] nc = br.readLine().split("/");
							FoodOrderController.addCustomer(new CustomerDTO(nc[0], nc[1], nc[2], nc[3]));
							FoodOrderController.selectOne(nc[0]);
						} else if (inputNum1 == 2) {
							System.out.println("\n찾을 회원의 id 값을 입력하세요.");
							String inputId = br.readLine();
							FoodOrderController.selectOne(inputId);
						}

					} else if (answer.equals("Y") || answer.equals("y")) {
						System.out.println("\nid를 입력해주세요:");
						String id = br.readLine();

						if (CustomerDAO.selectOne(id) != null) {
							try {
								System.out.println("\n=====번호를 선택하세요=====");
								System.out.println("1) 회원정보수정\t 2) 회원탈퇴 \t 3) 가게조회 \t 4) 메뉴조회 \t 5) 주문조회 \t 6) 주문추가");
								int inputNum1 = Integer.parseInt(br.readLine());
								if (inputNum1 == 1) {
									System.out.println("\n수정 할 정보 선택 : 1(이름),2(핸드폰번호),3(주소)");
									inputNum1 = Integer.parseInt(br.readLine());

									if (inputNum1 == 1) {
										System.out.println("\n수정하실 이름으로 작성해주세요:");
										String name = (br.readLine());
										FoodOrderController.updateName(id, name);
										FoodOrderController.selectOne(id);
									} else if (inputNum1 == 2) {
										System.out.println("\n수정될 핸드폰 번호를 입력해주세요:");
										String phoneNum = br.readLine();
										FoodOrderController.updatePhone(id, phoneNum);
										FoodOrderController.selectOne(id);
									} else if (inputNum1 == 3) {
										System.out.println("\n수정될 주소를 오른쪽 양식에 맞게 입력해주세요:");
										String address = br.readLine();
										FoodOrderController.updateAddress(id, address);
										FoodOrderController.selectOne(id);
									}

								} else if (inputNum1 == 2) {
									System.out.println("\n탈퇴할 회원의 이름을 입력해주세요: ");
									String name = br.readLine();
									FoodOrderController.deleteCustomer(id, name);

								} else if (inputNum1 == 3) {
									System.out.println("\n=====번호를 선택하세요=====");
									System.out.println("1) 모든가게 조회\t 2) 특정가게 조회 ");
									int inputNum2 = Integer.parseInt(br.readLine());

									if (inputNum2 == 1) {
										FoodOrderController.getAllStore();
									} else if (inputNum2 == 2) {
										System.out.println("\n검색하고자 하는 가게 이름을 입력해주세요:");
										String storeName = br.readLine();
										FoodOrderController.getStore(storeName);
									}

								} else if (inputNum1 == 4) {
									System.out.println("\n=====번호를 선택하세요=====");
									System.out.println("1) 특정 가게 메뉴 조회 \t 2) 특정 단일 메뉴 조회 ");
									int inputNum2 = Integer.parseInt(br.readLine());

									if (inputNum2 == 1) {
										System.out.println("\n메뉴 조회를 원하는 가게 이름을 입력하세요:");
										String storeName = br.readLine();
										FoodOrderController.getStoreMenus(storeName);
									} else if (inputNum2 == 2) {
										System.out.println("\n메뉴 이름을 입력하세요:");
										String menuName = br.readLine();
										FoodOrderController.getMenu(menuName);
									}

								} else if (inputNum1 == 5) {
									System.out.println("\n=====번호를 선택하세요=====");
									System.out.println("1) 내 주문 조회 \t 2) 내주문 수정 \t 3) 내 주문 삭제 ");
									int inputNum2 = Integer.parseInt(br.readLine());

									if (inputNum2 == 1) {
										FoodOrderController.getMyOrders(id);
									} else if (inputNum2 == 2) {
										System.out.println("\n수정하고자하는 주문번호를 입력하세요:");
										int order_no = Integer.parseInt(br.readLine());

										if (OrdersDAO.getOrders(order_no) != null ){
											System.out.println("\n원하는 결제방법을 입력하세요:");
											String method = br.readLine();

											FoodOrderController.updateOrders(order_no, method);
											FoodOrderController.getMyOrders(id);
										} else{
											System.out.println("해당주문번호가 존재하지 않습니다. 다시 확인해 주세요.");
										}
									} else if (inputNum2 == 3) {
										System.out.println("\n삭제하고자 하는 번호를 입력하세요:");
										int order_no = Integer.parseInt(br.readLine());
										FoodOrderController.deleteOrders(order_no);
									}

								} else if (inputNum1 == 6) {
									System.out.println("\n추가할 주문 정보를 오른쪽 양식에 맞게 입력하세요. 가게이름/메뉴이름/결제방법 \n");
									String[] orderInfo = br.readLine().split("/");
									FoodOrderController
											.addOrders(new OrdersDTO(id, orderInfo[0], orderInfo[1], orderInfo[2]));
								}

							} catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
//								e.printStackTrace();
								EndView.showError("포멧에 맞게 입력해주세요.");
							}
						}

						else if (CustomerDAO.selectOne(id) == null) {
							System.out.println("id를 확인해주세요!!");
						}

					}
				}
				// 점주
				if (inputNum == 2) {
					System.out.println("\n가게 이름를 입력해주세요.");
					String storeName = br.readLine();

					System.out.println("\n=====번호를 선택하세요=====");
					System.out.println("1) 가게 주문 전체 조회\t 2) 가게 메뉴 변동(추가/수정/삭제) \t 3) 가게 주소 변경");
					int inputNum1 = Integer.parseInt(br.readLine());

					if (inputNum1 == 1) {
						FoodOrderController.getStoreOrders(storeName);

					} else if (inputNum1 == 2) {
						System.out.println("\n=====번호를 선택하세요=====");
						System.out.println("1) 새로운 메뉴\t 2) 메뉴 가격 수정 \t 3) 기존 메뉴 삭제");
						int inputNum2 = Integer.parseInt(br.readLine());

						if (inputNum2 == 1) {
							System.out.println("\n추가할 메뉴 정보를 오른쪽 양식에 맞게 입력하세요. 메뉴이름/가격\n");
							String[] menuInfo = br.readLine().split("/");
							FoodOrderController
									.addMenu(new MenuDTO(menuInfo[0], Integer.parseInt(menuInfo[1]), storeName));
							FoodOrderController.getStoreMenus(storeName);
						} else if (inputNum2 == 2) {
							System.out.println("\n가격 수정할 메뉴 정보를 오른쪽 양식에 맞게 입력하세요. 메뉴이름/가격\n");
							String[] menuInfo = br.readLine().split("/");
							FoodOrderController.updateMenu(menuInfo[0], Integer.parseInt(menuInfo[1]));
							FoodOrderController.getStoreMenus(storeName);
						} else if (inputNum2 == 3) {
							System.out.println("\n삭제할 메뉴 이름을 입력하세요:\n");
							String menuName = br.readLine();
							FoodOrderController.deleteMenu(menuName);
							FoodOrderController.getStoreMenus(storeName);
						}

					} else if (inputNum1 == 3) {
						System.out.println("\n변경된 주소를 입력하세요:\n");
						String address = br.readLine();
						FoodOrderController.updateStore(storeName, address);
						FoodOrderController.getStore(storeName);
					}

				}

				// 관리자
				else if (inputNum == 3) {
					System.out.println("**********관리자 로그인**********");
					System.out.println("\n=====사용할 기능 번호를 선택하세요=====");
					System.out.println("1) 회원정보 전체 조회 \t 2) 회원정보 수정 \t 3) 회원정보 삭제 \t 4) 특정 회원 정보 조회 \n"
							+ "5) 모든 가게 정보 조회 \t 6) 특정 가게 정보 조회 \t 7) 특정 가게 정보 삭제 \t 8) 특정 가게 정보 추가 \n"
							+ "9) 존재하는 전체 메뉴 조회 \t 10) 특정 가게 메뉴 전체 조회 \t 11) 특정 메뉴 단일 조회 \n"
							+ "12) 전체 주문 조회 \t 13) 특정 가게 주문 전체 조회 \t 14)특정 주문 단일 조회");
					int inputNum1 = Integer.parseInt(br.readLine());
					switch (inputNum1) {
					case 1:
						FoodOrderController.selectAll();
						break;
					case 2:
						System.out.println("\n수정 할 정보 선택 : 1(이름),2(핸드폰번호),3(주소)");
						inputNum1 = Integer.parseInt(br.readLine());
						if (inputNum1 == 1) {
							System.out.println("\n수정할 회원 id와 수정될 이름을 오른쪽 양식에 맞게 입력하세요. id/이름");
							String[] nc = (br.readLine()).split("/");
							FoodOrderController.updateName(nc[0], nc[1]);
							FoodOrderController.selectOne(nc[0]);
						} else if (inputNum1 == 2) {
							System.out.println("\n수정할 회원 id와 수정될 핸드폰 번호를 오른쪽 양식에 맞게 입력하세요. id/핸드폰번호");
							String[] nc = (br.readLine()).split("/");
							FoodOrderController.updatePhone(nc[0], nc[1]);
							FoodOrderController.selectOne(nc[0]);
						} else if (inputNum1 == 3) {
							System.out.println("\n수정할 회원 id와 수정될 주소를 오른쪽 양식에 맞게 입력하세요. id/주소");
							String[] nc = (br.readLine()).split("/");
							FoodOrderController.updateAddress(nc[0], nc[1]);
							FoodOrderController.selectOne(nc[0]);
						}
						break;
					case 3:
						System.out.println("\n탈퇴할 회원의 id와 이름을 오른쪽 양식에 맞게 입력하세요. id/이름");
						System.out.println("\n");
						String[] nc = (br.readLine()).split("/");
						FoodOrderController.deleteCustomer(nc[0], nc[1]);
						break;
					case 4:
						System.out.println("\n조회할 회원 id 입력하세요:");
						String id = br.readLine();
						FoodOrderController.selectOne(id);
						break;
					case 5:
						System.out.println(
								"=============================================모든가게 정보=============================================");
						FoodOrderController.getAllStore();
						break;
					case 6:
						System.out.println("\n검색할 가게 이름을 입력하세요:");
						FoodOrderController.getStore(br.readLine());
						break;
					case 7:
						System.out.println("\n삭제할 가게 이름을 입력하세요:");
						String storeName = br.readLine();
						FoodOrderController.deleteStore(storeName);
						FoodOrderController.getAllStore();
						break;
					case 8:
						System.out.println("\n오른쪽 양식에 맞춰 작성해주세요. 가게이름/가게위치/연락처 \n");
						String[] storeInfo = br.readLine().split("/");
						FoodOrderController.insertStore(new StoreDTO(storeInfo[0], storeInfo[1], storeInfo[2]));
						FoodOrderController.getAllStore();
						break;
					case 9:
						System.out.println("\n================전체 메뉴 목록=================");
						FoodOrderController.getAllMenus();
						break;
					case 10:
						System.out.println("\n메뉴를 검색할 가게 이름을 입력하세요.");
						String findStore = br.readLine();
						FoodOrderController.getStoreMenus(findStore);
						break;
					case 11:
						System.out.println("\n검색할 메뉴의 이름을 입력하세요.");
						FoodOrderController.getMenu(br.readLine());
						break;
					case 12:
						System.out.println("\n==========접수된 전체 주문 조회============");
						FoodOrderController.getAllOrders();
						break;
					case 13:
						System.out.println("\n주문을 확인할 가게 이름을 입력하세요.");
						FoodOrderController.getStoreOrders(br.readLine());
						break;
					case 14:
						System.out.println("\n확인할 주문 번호를 입력해주세요.");
						FoodOrderController.getOrders(Integer.parseInt(br.readLine()));
						break;
					}

				} else if (inputNum == 0) {
					System.out.println("시스템 종료");
					break;

				}
			}
		} catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException | SQLException e) {
			e.printStackTrace();
			EndView.showError("포멧에 맞게 입력해주세요.");

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
	
	// 회원 가입
	public static void addCustomer(CustomerDTO customer) {
		boolean result = false;
		String function = "가입";

		try {
			result = CustomerDAO.addCustomer(customer);
			if (result) {
				logger.trace("[id]" + customer.getId() + "[name]" + customer.getName() + ": 회원 가입 성공");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<회원가입 에러 발생>>>");
			logger.error("[id]" + customer.getId() + "[name]" + customer.getName() + " <<<회원 가입 에러 발생>>>");
		}finally {
			EndView.showMessage(result, customer.getName(), function);
		}
	}

	// 회원 탈퇴
	public static void deleteCustomer(String id, String name) {
		boolean result = false;
		String function = "탈퇴";

		try {
			result = CustomerDAO.deleteCustomer(id, name);
			if (result) {
				logger.trace("[name]" + name + ": 회원 탈퇴 정상 성공");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<회원 탈퇴 실패>>");
			EndView.showError("잔여 주문 내역이 존재하는지 확인해주세요.");
			logger.error("[name]" + name + " <<<회원 탈퇴 실패>>");
		}finally {
			EndView.showMessage(result, id, function);
		}
	}

	// 회원 정보 변경(이름)
	public static void updateName(String id, String name) {
		boolean result = false;
		String function = "이름 변경";

		try {
			result = CustomerDAO.updateName(id, name);
			if (result) {
				logger.trace("[id]" + id + "[name]" + name + ": 이름 변경 성공");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<이름 변경 실패>>>");
			logger.error("[id]" + id + "[name]" + name + " <<<이름 변경 실패>>>");
		}finally {
			EndView.showMessage(result, id, function);
		}
	}

	// 회원 정보 변경(전화번호)
	public static void updatePhone(String id, String phone) {
		boolean result = false;
		String function = "전화번호 변경";

		try {
			result = CustomerDAO.updatePhone(id, phone);
			if (result) {
				logger.trace("[id]" + id + "[phone]" + phone + ": 전화번호 변경 성공");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<전화번호 변경 실패>>>");
			logger.error("[id]" + id + "[phone]" + phone + " <<<전화번호 변경 실패>>>");
		}finally {
			EndView.showMessage(result, id, function);
		}
	}

	// 회원 정보 변경(주소)
	public static void updateAddress(String id, String address) {
		boolean result = false;
		String function = "주소 변경";

		try {
			result = CustomerDAO.updateAddress(id, address);
			if (result) {
				logger.trace("[id]" + id + "[address]" + address + ": 주소 변경 성공");
			}
		}catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<주소 변경 실패>>>");
			logger.error("[id]" + id + "[address]" + address + " <<<주소 변경 실패>>>");
		}finally {
			EndView.showMessage(result, id, function);
		}
	}

	// 회원 정보 전체 조회
	public static void selectAll() {
		ArrayList<CustomerDTO> all = null;

		try {
			all = CustomerDAO.selectAll();
			if (all.size() != 0) {
				EndView.printAll(all);
				logger.trace("모든 회원 정보 조회 시도");
			}
			else {
				System.out.println("존재하는 회원이 없습니다.");
				logger.trace("모든 회원 정보 조회 시도");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<회원 정보 전체 조회 실패>>>");
			logger.error("<<<회원 정보 전체 조회 실패>>>");
		}
	}

	// 회원 정보 단일 조회
	public static void selectOne(String id) {
		CustomerDTO one = null;

		try {
			one = CustomerDAO.selectOne(id);
			if (one != null) {
				EndView.printOne(one);
				logger.trace("[id]" + id + ": 회원 정보 조회 시도");
			}
			else {
				System.out.println("입력 정보와 일치하는 회원이 없습니다.");
				logger.trace("[id]" + id + ": 존재하지 않는 회원 정보 단일 조회 시도");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<회원 정보 단일 조회 실패>>>");
			logger.error("[id]" + id + " <<<회원 정보 단일 조회 실패>>>");
		}
	}
	
	
	/**
	 * Menu - CRUD
	 */
	
	// 새로운 메뉴 추가
	public static void addMenu(MenuDTO menu) {
		String function = "메뉴 추가";
		boolean result = false;
	
		try{
			result = MenuDAO.addMenu(menu);
			if(result) {
				logger.trace("[menuName]" + menu.getMenuName() + ": 메뉴 추가 성공");
			}
		}catch(SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<메뉴 저장 실패>>>");
			logger.error("[menu]" + menu.getMenuName() + " <<<메뉴 저장 실패>>>");
		}finally {
			EndView.showMessage(result, menu.getMenuName(), function);
		}
	}
	
	// 기존 메뉴 수정(가격)
	public static void updateMenu(String menuName, int menuPrice) {
		String function = "가격 수정";
		boolean result = false;
		
		try{
			result = MenuDAO.updateMenu(menuName, menuPrice);
			if(result) {
				logger.trace("[menuName]" + menuName + "[menuPrice]" + menuPrice + ": 메뉴 추가 성공");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<메뉴 가격 수정 실패>>>");
			logger.error("[menuName]" + menuName + "[menuPrice]" + menuPrice + " <<<메뉴 가격 수정 실패>>>");
			EndView.showMessage(result, menuName, function);
		}
	}
	
	// 기존 메뉴 삭제
	public static void deleteMenu(String menuName) {
		String function = "메뉴 삭제";
		boolean result = false;
		
		try{
			result = MenuDAO.deleteMenu(menuName);
			if(result) {
				logger.trace("[menuName]" + menuName + ": 메뉴 삭제 성공");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<매뉴 삭제 실패>>>");
			logger.error("[menuName]" + menuName + ": <<<매뉴 삭제 실패>>>");
		}finally {
			EndView.showMessage(result, menuName, function);
		}
	}
	
	
	// 특정 메뉴 단일 조회
	public static void getMenu(String menuName) {
		MenuDTO one = null;
		
		try {
			one = MenuDAO.getMenu(menuName);
			if(one != null) {
				EndView.printOne(one);
				logger.trace("[menuName]" + menuName + ": 메뉴 정보 조회 시도");
			}else {
				System.out.println("입력 정보와 일치하는 메뉴가 없습니다.");
				logger.trace("[menuName]" + menuName + ": 존재하지 않는 메뉴 정보 단일 조회 시도");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<메뉴 정보 단일 조회 실패>>>");
			logger.error("[menuName]" + menuName + " <<<메뉴 정보 단일 조회 실패>>>");
		}
	}
	
	// 특정 가게 메뉴 다중 조회
	public static void getStoreMenus(String storeName) {
		ArrayList<MenuDTO> all = null;
		
		try{
			all = MenuDAO.getStoreMenus(storeName);
			if(all.size() != 0) {
				EndView.printAll(MenuDAO.getStoreMenus(storeName));
				logger.trace("[storeName]" + storeName + ": 가게 메뉴 정보 조회 시도");
			}else {
				System.out.println("입력 정보와 일치하는 메뉴가 없습니다.");
				logger.trace("[storeName]" + storeName + ": 존재하지 않는 메뉴 정보 전체 조회 시도");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<특정 가게 메뉴 정보 다중 조회 실패>>");
			logger.error("[storeName]" + storeName + ": <<특정 가게 메뉴 정보 다중 조회 실패>>");
		}
	}
	
	// 전체 메뉴 다중 조회
	public static void getAllMenus(){
		ArrayList<MenuDTO> all = null;
		
		try{
			all = MenuDAO.getAllMenus();
			if(all.size() != 0) {
				EndView.printAll(MenuDAO.getAllMenus());
				logger.trace("모든 메뉴 정보 조회 시도");
			}else {
				System.out.println("존재하는 메뉴가 없습니다.");
				logger.trace("모든 메뉴 정보 조회 시도");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<메뉴 정보 전체 조회 실패>>>");
			logger.error("<<<메뉴 정보 전체 조회 실패>>>");
		}
	}
	
	
	/**
	 * Orders - CRUD
	 */
	
	// 새로운 주문 추가(출력 방법 다름)
	public static void addOrders(OrdersDTO order) {
		try{
			boolean result = OrdersDAO.addOrders(order);
			if(result) {
				logger.trace("[order_no]" + order.getOrder_no() + ": 주문 추가 성공");
			}
		}catch(SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<주문 추가 실패>>>");
			logger.error("[order_no]" + order.getOrder_no() + " <<<주문 추가 실패>>>");
		}
		
	}

	// 기존 주문 변경(결제 방식)
	public static void updateOrders(int order_no, String payMethod) {
		String function = "결제 방식 변경";
		boolean result = false;

		try{
			result = OrdersDAO.updateOrders(order_no, payMethod);
			if(result) {
				logger.trace("[order_no]" + order_no + ": 결제 방식 변경 성공");
			}
		}catch(Exception e){
//			e.printStackTrace();
			EndView.showError("<<<결제 방식 변경 실패>>>");
			logger.error("[order_no]" + order_no + " <<<결제 방식 변경 실패>>>");
		}finally {
			EndView.showMessage(result, order_no, function);
		}
	}
	
	// 기존 주문 삭제
	public static void deleteOrders(int order_no) {
		String function = "주문 삭제";
		boolean result = false;
		
		try{
			result = OrdersDAO.deleteOrders(order_no);
			if(result) {
				logger.trace("[order_no]" + order_no + ": 주문 삭제 성공");
			}
		}catch(SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<주문 삭제 실패>>>");
			logger.error("[order_no]" + order_no + " <<<주문 삭제 실패>>>");
		}finally {
			EndView.showMessage(result, order_no, function);
		}
	}
	
	// 특정 주문 단일 조회
	public static void getOrders(int order_no) {
		OrdersDTO one = null;
		
		try {
			one = OrdersDAO.getOrders(order_no);
			if(one != null) {
				EndView.printOne(one);
				logger.trace("[order_no]" + order_no + ": 주문 정보 조회 시도");
			}else {
				System.out.println("입력 정보와 일치하는 주문이 없습니다.");
				logger.trace("[order_no]" + order_no + ": 존재하지 않는 주문 정보 전체 조회 시도");
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<주문 정보 단일 조회 실패>>>");
			logger.error("[order_no]" + order_no + " <<<주문 정보 단일 조회 실패>>>");
		}
	}
	
	// 특정 가게 주문 다중 조회
	public static void getStoreOrders(String storeName) {
		ArrayList<OrdersDTO> all = null;
		
		try{
			all = OrdersDAO.getStoreOrders(storeName);
			if(all.size() != 0) {
				EndView.printAll(all);
				logger.trace("[storeName]" + storeName + ": 주문 정보 전체 조회 시도");	
			}else {
				System.out.println("입력 정보와 일치하는 주문이 없습니다.");
				logger.trace("[storeName]" + storeName + ": 존재하지 않는 주문 정보 전체 조회 시도");
			}
		}catch (SQLException e){
//			e.printStackTrace();
			EndView.showError("<<특정 가게 주문 정보 다중 조회 실패>>");
			logger.error("[storeName]" + storeName + ": <<특정 가게 주문 정보 다중 조회 실패>>");
		}
	}
	
	// 특정 회원 주문 다중 조회
		public static void getMyOrders(String id) {
			ArrayList<OrdersDTO> all = null;
			
			try{
				all = OrdersDAO.getMyOrders(id);
				if(all.size() != 0) {
					EndView.printAll(all);	
					logger.trace("[id]" + id + ": 회원 주문 정보 전체 조회 시도");
				}else {
					System.out.println("입력 정보와 일치하는 주문이 없습니다.");
					logger.trace("[id]" + id + ": 존재하지 않는 주문 정보 전체 조회 시도");
				}
			}catch (SQLException e){
//				e.printStackTrace();
				EndView.showError("<<특정 회원 주문 정보 다중 조회 실패>>");
				logger.error("[id]" + id + " <<특정 회원 주문 정보 다중 조회 실패>>");
			}
		}
	
	// 전체 주문 다중 조회
	public static void getAllOrders(){
		ArrayList<OrdersDTO> all = null;
		
		try{
			all = OrdersDAO.getAllOrders();
			if(all.size() != 0) {
				EndView.printAll(all);
				logger.trace("모든 주문 정보 조회 시도");	
			}else {
				System.out.println("존재하는 주문이 없습니다.");
				logger.trace("모든 주문 정보 조회 시도");
			}
		}catch (SQLException e){
//			e.printStackTrace();
			EndView.showError("<<<주문 정보 전체 조회 실패>>>");
			logger.error("<<<주문 정보 전체 조회 실패>>>");
		}
	}
	
	/**
	 * Store - CRUD
	 */
	
	// 전체 가게 정보 조회
	public static void getAllStore(){
		ArrayList<StoreDTO> all = null;
		
		try {
			all = StoreDAO.getAllStore();
			if(all.size() != 0) {
				EndView.printAll(all);
				logger.trace("모든 가게 정보 조회 시도");
			}else {
				System.out.println("존재하는 가게가 없습니다.");
				logger.trace("모든 가게 정보 조회 시도");
			}
		}catch(SQLException s) {
//			s.printStackTrace();
			EndView.showError("<<<가게 정보 전체 조회 실패>>>");
			logger.error("<<<가게 정보 전체 조회 실패>>>");
		}
	}
	
	// 가게 정보 단일 조회
	public static void getStore(String storeName) {
		StoreDTO one = null;
		
		try {
			one = StoreDAO.getStore(storeName);
			if(one != null) {
				EndView.printOne(one); 
				logger.trace("[storeName]" + storeName + ": 가게 정보 조회 시도");
			}else {
				System.out.println("입력 정보와 일치하는 가게가 없습니다.");
				logger.trace("[storeName]" + storeName + ": 존재하지 않는 메뉴 가게 정보 조회 시도");
			}			 
		}catch(SQLException e) {
//			e.printStackTrace();
			EndView.showError("<<<가게 정보 단일 조회 실패>>>");
			logger.error("[storeName]" + storeName + ": <<<가게 정보 단일 조회 실패>>>");
		}
	}
	
	// 가게 정보 변경(주소)
	public static void updateStore(String storeName, String address) {
		boolean result = false;
		String function = "주소 변경";
		
		try {
			result = StoreDAO.updateStore(storeName, address);
			if(result) {
				logger.trace("[storeName]" + storeName + "[address]" + address + ": 주소 변경 성공");
			}
		}catch(SQLException s) {
//			s.printStackTrace();
			EndView.showError("<<<주소 변경 실패>>>");
			logger.error("[storeName]" + storeName + "[address]" + address + " <<<주소 변경 실패>>>");
		}finally {
			EndView.showMessage(result, storeName, function);
		}
	}
	
	// 가게 추가 
	public static void insertStore(StoreDTO store)  {
		boolean result = false;
		String function = "가게 추가";
		
		try {
			result = StoreDAO.addStore(store);
			if(result) {
				logger.trace("[storeName]" + store.getStoreName() + ": 가게 추가 성공");
			}
		}catch(SQLException e) {
//			s.printStackTrace();
			EndView.showError("<<<가게 저장 실패>>>");
			logger.error("[storeName]" + store.getStoreName() + " <<<가게 저장 실패>>>");
		}finally {
			EndView.showMessage(result, store.getStoreName(), function);
		}
	}
	
	// 가게 삭제
	public static void deleteStore(String storeName) {
		String function = "가게 삭제";
		boolean result = false;
		
		try {
			if(result) {
			result = StoreDAO.deleteStore(storeName);
			logger.trace("[storeName]" + storeName + ": 가게 삭제 성공");
			}
		}catch(SQLException | NumberFormatException s){
//			s.printStackTrace();
			EndView.showError("<<<가게 삭제 실패>>");
			EndView.showError("잔여 주문 내역이 존재하는지 확인해주세요.");
			logger.error("[storeName]" + storeName + " <<<가게 삭제 실패>>");
		}
		finally {
			EndView.showMessage(result, storeName, function);
		}
		
	}
	
}
