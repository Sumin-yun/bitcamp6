package com.eomcs.pms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Comment;

public class NetBoardDao implements BoardDao{

  Connection con;

  public NetBoardDao(Connection con) {
    this.con =  con;
  }

  @Override
  public void insert(Board board) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        " insert "
            + "into board "
            + "(member_no,title,content)"
            + "values(?,?,?)")){

      stmt.setInt(1, board.getWriter().getNumber());
      stmt.setString(2, board.getTitle());
      stmt.setString(3, board.getContent());


      if(stmt.executeUpdate() == 0) {
        throw new Exception("게시글 저장 실패");
      }
    }

    //    requestAgent.request("addNumber.board", null);
    //    int no = requestAgent.getObject(Integer.class);
    //    board.setBoardNumber(no);
    //
    //    requestAgent.request("board.insert", board);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      throw new Exception("게시글 데이터 저장 실패");
  }


  @Override
  public List<Board> findAll() throws Exception {
    //    requestAgent.request("board.selectList", null);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception("게시글 데이터 조회 실패");
    //    }
    //    return new ArrayList<>(requestAgent.getObjects(Board.class));
    return null;
  }

  @Override
  public Board findByNo(int no) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));
    //    requestAgent.request("board.selectOne", params);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      return null;
    //    }
    //    return requestAgent.getObject(Board.class);
    return null;
  }

  @Override
  public Board findByBoard(String keyword) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("keyword", keyword);
    //
    //    requestAgent.request("board.selectOne", params);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      return null;
    //    }
    //    return requestAgent.getObject(Board.class);
    return null;
  }

  @Override
  public void update(Board board) throws Exception {
    //    requestAgent.request("board.update", board);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception("게시글 데이터 변경 실패");
    //    }
  }

  @Override
  public void delete(int no) throws Exception {    
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("no", String.valueOf(no));
    //
    //    requestAgent.request("board.delete", params);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception("게시글 데이터 삭제 실패");
    //    }
  }

  // 댓글 등록
  @Override
  public void insert(Comment comment) throws Exception {
    //    requestAgent.request("board.comment.insert", comment);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)){
    //      throw new Exception("댓글 데이터 저장 실패");   }

  }

  // 댓글 조회
  @Override
  public List<Comment> findAll(int boardNo) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("boardNo", String.valueOf(boardNo));
    //
    //    requestAgent.request("board.comment.selectList", params);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      System.out.println(requestAgent.getObject(String.class));
    //      return null;
    //    }
    //    return new ArrayList<>(requestAgent.getObjects(Comment.class));
    return null;
  }

  // 댓글 변경
  @Override
  public void update(Comment comment) throws Exception {
    //    requestAgent.request("board.comment.update", comment);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      //      System.out.println(requestAgent.getObject(String.class));
    //      throw new Exception("댓글 데이터 변경 실패");
    //    }
  }

  // 댓글 삭제
  @Override
  public void delete(Comment comment) throws Exception {
    //    requestAgent.request("board.comment.delete", comment);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception("댓글 데이터 삭제 실패");
    //    }
  }

  // 댓글 선택
  @Override
  public Comment findCommentByNo(int boardNo, int commentNo) throws Exception {
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("boardNo", String.valueOf(boardNo));
    //    params.put("commentNo", String.valueOf(commentNo));
    //
    //    requestAgent.request("board.comment.selectOne", params);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      return null;
    //    }
    //    return requestAgent.getObject(Comment.class);
    return null;
  }

  @Override
  public void like(Board board) throws Exception {
    //
    //    requestAgent.request("board.comment.like", board);
    //
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception("좋아요 데이터 변경 실패");
    //    }
  }
}
