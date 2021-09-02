package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.util.Prompt;

public class CartDetailHandler extends AbstractCartHandler {

  public CartDetailHandler(List<Cart> cartList) {
    super(cartList);
  }

  public void execute() {
    if (App.getLoginUser().getAuthority() != 1) {
      System.out.println("권한이 없습니다. 구매자 기능입니다.");
      return;
    }
    System.out.println("[장바구니 상세보기]");
    Cart cart = findByCart(Prompt.inputString("상품명 : "));

    if (cart == null) {
      System.out.println("장바구니에 해당 상품이 없습니다.");
      return;
    }

    System.out.printf("상품명: %s입니다.\n", cart.stock.product.getProductName());
    System.out.printf("수량: %s입니다.\n", cart.getCartStocks());
    System.out.printf("총액: %s입니다.\n", cart.getCartPrice());
    System.out.printf("등록일: %s입니다.\n", cart.getRegistrationDate());
  }
}