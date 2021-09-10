package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.pms.domain.Seller;
import com.eomcs.util.Prompt;


public abstract class AbstractBookingHandler implements Command {

  List <Seller> sellerList;
  List <BookingList> allBookingList;
  public AbstractBookingHandler(List <Seller> sellerList, List <BookingList> allBookingList) {
    this.sellerList = sellerList;
    this.allBookingList = allBookingList;
  }

  protected Booking findBooking(String bookingName) {
    BookingList bookingList = findById(App.getLoginUser().getId());
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getCart().getStock().getProduct().getProductName().equals(bookingName)) {
        return booking;
      }
    }
    return null;
  }

  protected BookingList findById(String id) {
    for (BookingList bookingList : allBookingList) {
      if (bookingList.getId().equals(id)) {
        return bookingList;
      }
    }
    return null;
  }

  protected BookingList putBookingListById(String id, Booking buyerBooking) {
    for (BookingList bookingList : allBookingList) {
      if (bookingList.getId().equals(id)) {
        bookingList.getBooking().add(buyerBooking);
        return bookingList;
      }
    }
    return null;
  }

  protected int checkHour (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1 || num > 24) {  
        System.out.println("입력하신 수는 유효하지 않습니다"); 
        continue;
      }           
      return num;       
    }
  }

  protected int checkMinute (String label) {
    while(true) {
      int num = Prompt.inputInt(label);
      if(num < 1 || num > 60) {  
        System.out.println("입력하신 수는 유효하지 않습니다"); 
        continue;
      }           
      return num;       
    }
  }

  protected Seller findSellerInfo(String sellerId) {
    for (Seller seller : sellerList) {
      if (seller.getName().equals(sellerId)){
        return seller;
      }
    }
    return null;
  }
}





