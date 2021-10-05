package com.eomcs.pms.table;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.domain.Booking;
import com.eomcs.pms.domain.BookingList;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

public class BookingTable extends JsonDataTable<BookingList> implements DataProcessor {

  public BookingTable() {
    super("allBookingList.json", BookingList.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    // TODO Auto-generated method stub
    switch (request.getCommand()) {
      //로딩오류가 나면 새로 생성하기.
      case "booking.List.insert" : insertList(request, response); break;
      case "booking.insert" : insert(request, response); break;
      case "booking.selectList" : selectList(request, response); break;
      case "booking.selectAllList" : selectAllList(request, response); break;
      case "booking.selectOne" : selectOne(request, response); break;
      //      case "booking.update" : update(request, response); break;
      case "booking.delete" : delete(request, response); break;
      case "booking.List.delete" : deleteList(request, response); break;

      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insertList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");

    BookingList bookingList = new BookingList();
    bookingList.setId(id);
    list.add(bookingList);
    response.setStatus(Response.SUCCESS);
  }

  private void deleteList(Request request, Response response) throws Exception {
    String id = request.getParameter("id");
    int index = indexOf(id);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void insert(Request request, Response response) throws Exception {
    Booking booking = request.getObject(Booking.class);
    BookingList bookingList = findById(booking.getMineId());
    // stock numbering
    booking.setBookingNumber(bookingList.getTotalBookingNumber());
    bookingList.setTotalBookingNumber(booking.getBookingNumber()+1);
    bookingList.getBooking().add(booking);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception{
    String id = request.getParameter("id");
    BookingList bookingList = findById(id);
    response.setStatus(Response.SUCCESS);
    response.setValue(bookingList);
  }

  private void selectAllList(Request request, Response response) throws Exception{
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) {
    int no = Integer.parseInt(request.getParameter("no"));
    String id = request.getParameter("id");

    Booking booking = findByNoId(no, id);
    if (booking != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(booking);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 예약이 없습니다.");
    }
  }

  private BookingList findById(String id) {
    for (BookingList bookingList : list) {
      if (bookingList.getId().equals(id)) {
        return bookingList;
      }
    }
    return null;
  }

  private Booking findByNoId(int no, String id) {
    BookingList bookingList = findById(id);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == no) {
        return booking;
      }
    }
    return null;
  }

  private void delete(Request request, Response response) {

  }

  // 먼저 buyer 기준으로만.
  protected List<Booking> findBookingBuyer (int No, String firstId, String secondId, boolean delete) {
    List<Booking> twoBookingList = new ArrayList<>();
    BookingList bookingList = findById(firstId);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == No) {
        twoBookingList.add(booking);
        if (delete) {
          bookingList.getBooking().remove(booking);
        }
        bookingList = findById(secondId);
        for (Booking booking2 : bookingList.getBooking()) {
          if (booking2.getMineId().equals(firstId)
              && booking2.getCart().getStock().getProduct().getProductName().equals(
                  booking.getCart().getStock().getProduct().getProductName())) {
            twoBookingList.add(booking2);
            if (delete) {
              bookingList.getBooking().remove(booking2);
            }
            return twoBookingList;
          }
        }
      }
    }
    return null;
  }

  // 먼저 seller 기준으로만.
  protected List<Booking> findBookingSeller (int No, String firstId, String secondId, boolean delete) {
    List<Booking> twoBookingList = new ArrayList<>();
    BookingList bookingList = findById(firstId);
    for (Booking booking : bookingList.getBooking()) {
      if (booking.getBookingNumber() == No) {
        twoBookingList.add(booking);
        if (delete) {
          bookingList.getBooking().remove(booking);
        }
        bookingList = findById(secondId);
        for (Booking booking2 : bookingList.getBooking()) {
          if (booking2.getCart().getSellerId().equals(firstId)
              && booking2.getCart().getStock().getProduct().getProductName().equals(
                  booking.getCart().getStock().getProduct().getProductName())) {
            twoBookingList.add(booking2);
            if (delete) {
              bookingList.getBooking().remove(booking2);
            }
            return twoBookingList;
          }
        }
      }
    }
    return null;
  }

  private int indexOf(String id) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getId().equals(id)) {
        return i;
      }
    }
    return -1;
  }

}
