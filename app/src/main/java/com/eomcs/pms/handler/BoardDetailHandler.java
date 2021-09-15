package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.App;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardDetailHandler extends AbstractBoardHandler {

  BoardPrompt boardPrompt;
  MemberPrompt memberPrompt;
  public BoardDetailHandler(List<Board> boardList, BoardPrompt boardPrompt, MemberPrompt memberPrompt) {
    super(boardList);
    this.boardPrompt = boardPrompt;
    this.memberPrompt = memberPrompt;
  }

  @Override
  public void execute() {
    System.out.println("[게시글 상세보기] || 0. 이전");
    if (App.getLoginUser().isCommentUpdate()) {
      memberPrompt.changeCommentUpdate(App.getLoginUser().getId(), false);
    }
    int no = Prompt.inputInt("게시글 번호 : ");
    if (no==0) { return; }
    Board board = findByNo(no);

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    while(true) { 
      System.out.printf("제목 : %s\n", board.getTitle());
      System.out.printf("내용 : %s\n", board.getContent());
      System.out.printf("작성자 : %s\n", board.getWriter());
      System.out.printf("등록일 : %s\n", board.getRegistrationDate());

      board.setViews(board.getViews() + 1);
      System.out.printf("조회수 : %d\n", board.getViews());
      System.out.printf("좋아요 수 : %d\n", board.getLikes());
      System.out.printf("태그 : %s\n", board.getTag());
      CommentListHandler.list(board.getBoardNumber(), boardPrompt);
      System.out.println("\n< 1.좋아요 / 2.댓글등록 / 3.댓글수정 / 4.댓글삭제 >");
      int choose = Prompt.inputInt("선택 > ");
      if (App.getLoginUser().getAuthority()==0x01) {
        System.out.println("로그인 후 가능합니다.");
        return;
      }
      switch (choose) {
        case 0 : return;
        case 1 : LikeHandler.like(board); continue;
        case 2 : 
          CommentAddHandler.add(board.getBoardNumber(), boardPrompt); 
          memberPrompt.changeCommentUpdate(board.getWriter(), true);
          continue;
        case 3 : CommentUpdateHandler.update(board.getBoardNumber(), boardPrompt); continue; 
        case 4 : CommentDeleteHandler.delete(board.getBoardNumber(), boardPrompt); continue;
        default : System.out.println("잘못입력하셨습니다."); continue;
      }
    }
  }
}





