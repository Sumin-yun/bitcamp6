package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;
import com.eomcs.util.Prompt;

public class StockListHandler extends AbstractStockHandler {
  List<StockList> allStockList;
  public StockListHandler(List<StockList> allStockList, StockPrompt stockPrompt) {
    super(stockPrompt);
    this.allStockList = allStockList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String nowLoginId = App.getLoginUser().getId();
    System.out.println("[재고 목록]");
    StockList stockList = allStockList.get(stockPrompt.findStockListById(nowLoginId));

    if (stockList.getSellerStock().size() == 0) {
      System.out.println("아직 추가한 상품이 없습니다.");
      return;
    }
    System.out.printf("%-3s\t%-6s\t%-6s\t%-3s\n",
        "번호", "상품명", "가격", "재고");
    System.out.println("--------------------------------------------------------------------------");

    for (Stock stock : stockList.getSellerStock()) {
      System.out.printf("%-3d\t%-6s\t%-6d\t%-3d\n", 
          stock.getStockNumber(),
          stock.getProduct().getProductName(), 
          stock.getPrice(), 
          stock.getStocks());
    }
    System.out.println();
    while(true) {
      System.out.println("1. 상세보기 / 이전(0)");
      int choose = Prompt.inputInt("선택 > ");
      System.out.println();
      switch(choose) {
        case 1 : 
          request.getRequestDispatcher("/stock/detail").forward(request); return;
        case 0 :
          return;
        default : 
          System.out.println("다시 선택해 주세요."); continue;
      }
    }
  }
}

