package com.eomcs.pms.handler;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardSearchHandler extends AbstractBoardHandler {

  public BoardSearchHandler(List<Board> boardList) {
    super(boardList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[게시글 검색] || 이전(0)");

    List<Integer> searchNo = new ArrayList<>();
    String input = Prompt.inputString("검색어 : ");
    System.out.println();
    if (input.equals("0")) { return; }
    while(true) {
      boolean resultSearch = false;
      for (Board board : boardList) {
        if (!board.getTitle().contains(input) &&
            !board.getContent().contains(input) &&
            !board.getTag().contains(input)) {

          continue;
        }
        resultSearch = true;
        System.out.printf("%-3s\t%-6s\t%-15s\t%-6s\t%-6s\n",
            "번호", "제목", "내용", "태그", "등록일");
        System.out.println("--------------------------------------------------------------------------");

        System.out.printf("%-3d\t%-6s\t%-15s\t%-6s\t%-6s\n", 
            board.getBoardNumber(), 
            board.getTitle(), 
            board.getContent(),
            board.getTag(),
            board.getRegistrationDate());
        searchNo.add(board.getBoardNumber());
      }
      if (!resultSearch) {
        System.out.println("검색 결과가 없습니다.\n");
        return;
      }
      System.out.println();
      request.getRequestDispatcher("/board/detail").forward(request);

    }
  }
}
