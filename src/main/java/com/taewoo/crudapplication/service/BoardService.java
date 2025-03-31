package com.taewoo.crudapplication.service;

import com.taewoo.crudapplication.dto.BoardRequestDto;
import com.taewoo.crudapplication.dto.BoardResponseDto;
import com.taewoo.crudapplication.entity.Board;
import com.taewoo.crudapplication.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto dto, Long userId) {
        Board board = new Board(dto, userId);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    public List<BoardResponseDto> getAllBoards() {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDto::new)
                .collect(Collectors.toList());
    }

    public BoardResponseDto getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto dto, Long userId) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        if (!board.getUserId().equals(userId)) {
            throw new RuntimeException("User not authorized to update this board");
        }
        board.update(dto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    @Transactional
    public void deleteBoard(Long id, Long userId) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        if (!board.getUserId().equals(userId)) {
            throw new RuntimeException("No authorized user");
        }
        boardRepository.delete(board);
    }
}
