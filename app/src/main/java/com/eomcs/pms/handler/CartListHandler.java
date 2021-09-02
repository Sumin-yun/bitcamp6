package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;

public class CartListHandler extends AbstractCartHandler {

  public CartListHandler(List<Cart> cartList) {
    super(cartList);
  }

  public void execute() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 목록]");

    for (Cart cart : cartList) {
      System.out.printf("%d, %s, %d, %d, %s\n", // 장바구니 번호, 상품명, 수량, 총액
          cart.getCartNumber(), 
          cart.stock.product.getProductName(), 
          cart.getCartStocks(), 
          cart.getCartPrice(),
          cart.getRegistrationDate());
    }
  }
}