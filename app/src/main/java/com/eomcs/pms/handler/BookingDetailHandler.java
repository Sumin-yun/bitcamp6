package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Buyer;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;

public class BookingDetailHandler extends AbstractBookingHandler {

  BookingPrompt bookingPrompt;
  MemberPrompt memberPrompt;
  public BookingDetailHandler(List <BookingList> allBookingList, BookingPrompt bookingPrompt, 
      MemberPrompt memberPrompt) {
    super(allBookingList);
    this.bookingPrompt = bookingPrompt;
    this.memberPrompt = memberPrompt;
  }
  @Override
  public void execute(CommandRequest request) throws Exception {
    if (App.getLoginUser().getAuthority()==Menu.ACCESS_BUYER) {
      System.out.println("[내 픽업 예약 상세보기]\n");

      int No = Prompt.inputInt("예약번호 :");
      Booking booking = bookingPrompt.findBookingByNo(No, App.getLoginUser().getId());

      if (booking == null) {
        System.out.println("해당 상품의 예약이 없습니다.");
        return;
      }

      String sellerId = booking.getCart().getSellerId();
      Seller seller = memberPrompt.findBySellerInfo(sellerId);

      System.out.printf("가게명: %s\n", seller.getBusinessName());
      System.out.printf("판매자: %s\n", seller.getNickname());
      System.out.printf("가게주소: %s\n", seller.getBusinessAddress());
      System.out.printf("가게번호: %s\n", seller.getBusinessPlaceNumber());
      System.out.printf("상품명: %s\n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("수량: %d\n", booking.getBookingStocks());
      System.out.printf("금액: %d원\n",  booking.getBookingPrice());
      System.out.println();

      request.setAttribute("bookingNo", No);
      while(true) {
        System.out.println("\n 예약변경(R) / 예약취소(D) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch(choose) {
          case "r" :
          case "R" : request.getRequestDispatcher("/booking/update").forward(request); return;
          case "d" :
          case "D" : request.getRequestDispatcher("/booking/delete").forward(request); return;
          case "0" : return;
        }
      }

    } else if (App.getLoginUser().getAuthority()==Menu.ACCESS_SELLER) {
      System.out.println("[고객 예약 상세보기]\n");

      int No = Prompt.inputInt("예약번호 :");
      Booking booking = bookingPrompt.findBookingByNo(No, App.getLoginUser().getId());

      if (booking == null) {
        System.out.println("해당 상품의 예약이 없습니다.");
        return;
      }

      Buyer buyer = memberPrompt.findByBuyerInfo(booking.getBuyerId());

      System.out.printf("예약자: %s\n", booking.getBuyerId());
      System.out.printf("연락처: %s\n", buyer.getPhoneNumber());
      System.out.printf("상품명: %s\n", booking.getCart().getStock().getProduct().getProductName());
      System.out.printf("수량: %d\n", booking.getBookingStocks());
      System.out.printf("금액: %d원\n",  booking.getBookingPrice());

      System.out.println();


      System.out.println("");

      request.setAttribute("bookingNo", No);
      while(true) {
        System.out.println("\n 예약변경(U) / 이전(0)");
        String choose = Prompt.inputString("선택 > ");
        System.out.println();
        switch(choose) {
          case "u" :
          case "U" : request.getRequestDispatcher("/booking/update").forward(request); return;
          case "0" : return;
        }
      }
    }
  }
}
