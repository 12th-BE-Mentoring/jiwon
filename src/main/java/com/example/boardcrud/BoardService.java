package com.example.boardcrud;

import com.example.boardcrud.dto.BoardCreateRequest;
import com.example.boardcrud.dto.BoardResponse;
import com.example.boardcrud.dto.BoardUpdateRequest;
import com.example.boardcrud.global.error.CustomException;
import com.example.boardcrud.global.error.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링 컨테이너에게 Service class라는 것을 알려줌
@Service
public class BoardService {

    // BoardRepository 사용
    private final BoardRepository boardRepository;

    // 생성자로 의존성 주입 (@RequiredArgsConstructor). 객체를 직접 만들지 않고 Spring이 넣어줌
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 생성 기능
    public void createBoard(BoardCreateRequest request) {
        // 요청 받은 데이터를 get으로 들고와서 board 객체 생성
        Board board = new Board(request.getTitle(), request.getContent(), request.getWriter());

        // DB에 저장
        boardRepository.save(board);
    }

    // 게시글 조회 기능
    public BoardResponse getBoard(Integer id) {
        // id 맞는 게시글을 조회하고 없으면 Error
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        // 조회한 게시글 정보를 BoardResponse DTO로 반환
        return new BoardResponse(board.getWriter(), board.getTitle(), board.getContent());
    }

    // 트랜잭션 단위로 실행하게 해서 성공 or 전체취소
    @Transactional
    public void updateBoard(Integer id, BoardUpdateRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        board.updateBoard(request.getTitle(), request.getContent());
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Integer id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
        boardRepository.delete(board);
    }

}