package com.eomcs.pms.handler;

import java.util.HashMap;
import java.util.Set;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.CartList;
import com.eomcs.pms.domain.SellerPrivacy;
import com.eomcs.pms.domain.Stock;
import com.eomcs.pms.domain.StockList;

public class StockPrompt {
  protected boolean removeStockById(String stockName, String id) {
    StockList stockList = App.allStockList.get(findStockListById(id));
    for (int i=0; i<stockList.getSellerStock().size(); i++) {
      if (stockList.getSellerStock().get(i).getProduct().getProductName().equals(stockName)) {
        App.allStockList.get(findStockListById(id)).getSellerStock().remove(i);
        return true;
      }
    }
    return false;
  }

  protected int[] getStockListSizeById(String nowLoginId) {
    int[] sizeIndex = new int[2];
    //    Privacy[] arr = App.privacyList.toArray(new Privacy[0]);
    for (int i=0; i<App.allStockList.size(); i++) {
      if (App.allStockList.get(i).getId().equals(nowLoginId)) {
        sizeIndex[0] = App.allStockList.get(i).getSellerStock().size()+1;
        sizeIndex[1] = i;
        return sizeIndex;
      }
    }
    return null;
  }

  public int findStockListById(String id) {
    for (int i=0; i<App.allStockList.size(); i++) {
      if (App.allStockList.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

  public Stock findStockById(String id, String stockName) {
    StockList stockList = App.allStockList.get(findStockListById(id));
    for (Stock stock : stockList.getSellerStock()) {
      if (stock.getProduct().getProductName().equals(stockName)) {
        return stock;
      }
    }
    return null;
  }

  public boolean findByStock (String ProductName, String nowLoginId) {
    for (int i=0; i<App.allStockList.size(); i++) {
      if (App.allStockList.get(i).getId().equals(nowLoginId)) {
        for (Stock stock : App.allStockList.get(i).getSellerStock()) {
          if (stock.getProduct().getProductName().equals(ProductName)) {
            return true;
          }
        }
      }
    }
    return false;
  }


  //--------상품 검색시 판매자정보 반환
  protected HashMap<String, Stock> findBySellerId (String StockName) {
    //    Stock stockName = findByStock(StockName);
    //    if (stockName==null) {
    //      System.out.println("존재하지 않는 상품입니다.");
    //      return null;
    //    }

    HashMap<String, Stock> hashStock= new HashMap<>();
    for (StockList stockList : App.allStockList) {
      for (Stock stock : stockList.getSellerStock()) {
        if (stock.getProduct().getProductName().equals(StockName)) {
          //          isStock = true;
          SellerPrivacy sellerInfo = findBySellerInfo(stockList.getId());
          System.out.printf("가게명 : %s, 판매자 : %s, 재고 : %s, 금액 : %d, 주소 : %s, 판매자연락처 : %s\n", 
              sellerInfo.getBusinessName(),
              stockList.getId(),
              stock.getStocks(),
              stock.getPrice(),
              sellerInfo.getBusinessAddress(),
              sellerInfo.getBusinessPlaceNumber());
          hashStock.put(sellerInfo.getBusinessName(), stock);
        }
      }
    }
    return hashStock;
  }

  public String findStoreName(Set<String> keySet, String storeName) {
    for (String str : keySet) {
      if (str.equals(storeName)) {
        return storeName;
      }
    }
    return null;
  }

  public SellerPrivacy findBySellerInfo (String SellerId) {
    for (SellerPrivacy member : App.sellerPrivacyList) {
      if (member.getName().equals(SellerId)){
        return member;
      }
    }
    return null;
  }

  public int findCartListById(String id) {
    for (CartList cartList : App.allCartList) {
      if (cartList.getId().equals(id)) {
        return cartList.getPrivacyCart().size()+1;
      }
    }
    return -1;
  }

  public SellerPrivacy findByPlaceName (String storeName) {
    for (SellerPrivacy member : App.sellerPrivacyList) {
      if (member.getBusinessName().equals(storeName)){
        return member;
      }
    }
    return null;
  }


  //입력한 문자열을 포함하면 adress 리턴.
  public HashMap<String, SellerPrivacy> findByAdress (String adress) {
    HashMap<String, SellerPrivacy> hashMap = new HashMap<>();
    for (SellerPrivacy member : App.sellerPrivacyList) {
      if((member.getBusinessAddress()).contains(adress)) {
        hashMap.put(member.getId(), member);
      }
    }
    return hashMap;
  }


  //  HashMap<String, Stock> hashStock= new HashMap<>();
  //  for (StockList stockList : App.allStockList) {
  //    for (Stock stock : stockList.getSellerStock()) {
  //      if (stock.getProduct().getProductName().equals(StockName)) {
  //        //          isStock = true;
  //        SellerPrivacy sellerInfo = findBySellerInfo(stockList.getId());
  //        System.out.printf("가게명 : %s, 판매자 : %s, 재고 : %s, 금액 : %d, 주소 : %s, 판매자연락처 : %s\n", 
  //            sellerInfo.getBusinessName(),
  //            stockList.getId(),
  //            stock.getStocks(),
  //            stock.getPrice(),
  //            sellerInfo.getBusinessAddress(),
  //            sellerInfo.getBusinessPlaceNumber());
  //        hashStock.put(sellerInfo.getBusinessName(), stock);
  //      }
  //    }
  //  }
  //  return hashStock;
  //}


}
