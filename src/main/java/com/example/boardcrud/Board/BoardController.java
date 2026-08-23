package com.example.boardcrud.Board;

import com.example.boardcrud.dto.BoardCreateRequest;
import com.example.boardcrud.dto.BoardResponse;
import com.example.boardcrud.dto.BoardUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// 이 컨트롤러의 기본 URL을 "/board" 로 지정
@RequestMapping("/board")
// @Controller + @ResponseBody(자바객체 > HTTP body) = 데이터를 직접 반환
@RestController
public class BoardController {

    // 보드 서비스 사용
    private final BoardService boardService;

    // 생성자로 의존성 주입
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 요청 성공하면 201 CREATED 반환
    @ResponseStatus(HttpStatus.CREATED)
    // POST 처리
    @PostMapping
    // @RequestBody:JSON > 자바객체. @Valid:유효성 검사
    public void createBoard(@RequestBody @Valid BoardCreateRequest request) {
        boardService.createBoard(request);
    }

    //board-id로 받는 이유 : 게시글 식별
    // 요청 성공하면 204 NO_CONTENT 반환
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // DELETE /board/{board-id} 요청 처리
    @DeleteMapping("/{board-id}")
    // @PathVariable:URL의 board-id 값을 말함
    public void deleteBoard(@PathVariable("board-id") Integer id) {
        boardService.deleteBoard(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{board-id}")
    public void updateBoard(@PathVariable("board-id") Integer id, @RequestBody @Valid BoardUpdateRequest request) {
        boardService.updateBoard(id,request);
    }

    // GET /board/{board-id} 요청 처리
    @GetMapping("/{board-id}")
    public BoardResponse getBoard(@PathVariable("board-id") Integer id) {
        return boardService.getBoard(id);
    }
}