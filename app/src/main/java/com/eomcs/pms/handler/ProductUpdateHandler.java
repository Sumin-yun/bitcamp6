package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Product;
import com.eomcs.util.Prompt;

public class ProductUpdateHandler extends AbstractProductHandler {
  ProductPrompt productPrompt;
  public ProductUpdateHandler(ProductPrompt productPrompt) {
    this.productPrompt = productPrompt;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("\n[상품 변경]");

    Product product = (Product) request.getAttribute("상품");

    if (product == null) {
      System.out.println("해당 상품이 존재하지 않습니다.");
      return;
    }

    String name = Prompt.inputString("상품이름(" + product.getProductName()  + ")? ");
    String kind = Prompt.inputString("주종(" + product.getProductType() + ")? ");
    String made = Prompt.inputString("원산지(" + product.getCountryOrigin() + ")? ");
    String grapes = Prompt.inputString("품종(" + product.getVariety() + ")? ");
    float abv = Prompt.inputFloat("알콜도수(" + product.getAlcoholLevel() + ")? ");
    int sweet = checkNum("당도(" + product.getSugerLevel() + ")? ");
    int acidic = checkNum("산도(" + product.getAcidity() + ")? ");
    int body = checkNum("바디감(" + product.getWeight() + ")? ");


    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("y")) {
      product.setProductName(name);
      product.setProductType(kind);
      product.setCountryOrigin(made);
      product.setVariety(grapes);
      product.setAlcoholLevel(abv);
      product.setSugerLevel(sweet);
      product.setAcidity(acidic);
      product.setWeight(body);

      System.out.println("상품정보를 변경하였습니다.");
      return;
    } else {
      System.out.println("상품정보 변경을 취소하였습니다.");
      return;
    }
  }
}













