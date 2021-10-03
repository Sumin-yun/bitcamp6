package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Stock;

public interface BookingDao {
  void insert(Stock stock) throws Exception;
  List<Stock> findAll() throws Exception;
  //  List<Product> findByKeyword() throws Exception;
  //  Product findByNo(int no) throws Exception;
  //  Product findByProduct(String name) throws Exception;
  //  void update(Product product) throws Exception;
  //  void delete(Product product) throws Exception;
}
