package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;

public class StockListHandler extends AbstractStockHandler {

  public StockListHandler(List<Stock> stockList) {
    super(stockList);
  }


  public void list() {
    if (App.getLoginUser().getAuthority() == 0) {
      System.out.println("해당 메뉴는 로그인 후 사용가능합니다.\n로그인 후 사용해주세요.");
      return;
    }
    System.out.println("[재고 목록]");

    for (Stock stock : stockList) {
      System.out.printf("%s, %d, %d, %d\n", 
          stock.getProduct().getProductName(), 
          stock.getStockNumber(), 
          stock.getPrice(), 
          stock.getStocks());
    }
  }

}





