package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.util.Prompt;

public class BookingUpdateHandler extends AbstractBookingHandler {

  BookingPrompt bookingPrompt;
  public BookingUpdateHandler(List <BookingList> allBookingList, BookingPrompt bookingPrompt) {
    super(allBookingList);
    this.bookingPrompt = bookingPrompt;
  }
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[예약 변경]");
    int No = (int) request.getAttribute("bookingNo");
    Booking booking = bookingPrompt.findBookingByNo(No, App.getLoginUser().getId());

    List<Booking> bookingList = null;
    if (App.getLoginUser().getAuthority() == Menu.ACCESS_BUYER) { 
      bookingList = bookingPrompt.findBookingBuyer(
          No, App.getLoginUser().getId(), booking.getCart().getSellerId(), false);

    } else {
      bookingList = bookingPrompt.findBookingSeller(
          No, App.getLoginUser().getId(), booking.getBuyerId(), false);
    }

    int bookingstocks = Prompt.inputInt(String.format("수량(변경 전 : %d) :", booking.getBookingStocks()));
    // 수량 변경시 판매자 재고를 넘지 않도록, 변경후 수량 반영
    //    if (sellerStock.getStocks() - bookingstocks<0) {
    //      System.out.println("재고가 부족합니다. 구매 수량을 확인해주세요.");
    //      return;
    //    }
    Date reservationDate = Prompt.inputDate("픽업날짜 변경 (기존 : " + booking.getBookingDate() + ") : ");
    int reservationHour = checkHour("픽업시간 변경 (기존 : " + booking.getBookingHour() + "시"+ ") : ");
    int reservationMinute = checkMinute("픽업시간 변경 (기존 : " + booking.getBookingMinute() + "분"+ ") : ");


    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("y")) {
      bookingList.get(0).setBookingDate(reservationDate);
      bookingList.get(0).setBookingHour(reservationHour);
      bookingList.get(0).setBookingMinute(reservationMinute);
      bookingList.get(0).setBookingStocks(bookingstocks);
      bookingList.get(0).setBookingPrice(booking.getCart().getStock().getPrice()*bookingstocks);


      bookingList.get(1).setBookingDate(reservationDate);
      bookingList.get(1).setBookingHour(reservationHour);
      bookingList.get(1).setBookingMinute(reservationMinute);
      bookingList.get(1).setBookingStocks(bookingstocks);
      bookingList.get(1).setBookingPrice(booking.getCart().getStock().getPrice()*bookingstocks);
      System.out.println("픽업 예약을 변경하였습니다.");
      return;
    } else {
      System.out.println("픽업 예약 변경을 취소하였습니다.");
      return;
    }
  }
}






