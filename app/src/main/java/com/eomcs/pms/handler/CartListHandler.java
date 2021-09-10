package com.eomcs.pms.handler;

import com.eomcs.pms.App;
import com.eomcs.pms.domain.Cart;
import com.eomcs.pms.domain.CartList;

public class CartListHandler extends AbstractCartHandler {
  SellerPrompt sellerPrompt;
  public CartListHandler(CartPrompt cartPrompt, SellerPrompt sellerPrompt) {
    super(cartPrompt);
    this.sellerPrompt = sellerPrompt;
  }
  @Override
  public void execute() {

    System.out.println("[장바구니 목록]");
    CartList cartList = cartPrompt.findCartListById(App.getLoginUser().getId());

    if (cartList.getPrivacyCart().size() == 0) {
      System.out.println("아직 추가한 장바구니가 없습니다.");
      return;
    }
    for (Cart cart : cartList.getPrivacyCart()) {
      System.out.printf(" %d, %d, %d, %s\n", // 장바구니 번호, 상품명, 수량, 총액
          cart.getCartNumber(), 
          sellerPrompt.findBySellerInfo(cart.getSellerId()).getBusinessName(),
          cart.getStock().getProduct().getProductName(), 
          cart.getCartStocks(), 
          cart.getCartPrice(),
          cart.getRegistrationDate());
    }
  }
}


