package com.taewoo.crudapplication.controller;

import com.taewoo.crudapplication.dto.BoardRequestDto;
import com.taewoo.crudapplication.dto.BoardResponseDto;
import com.taewoo.crudapplication.repository.UserRepository;
import com.taewoo.crudapplication.service.BoardService;
import com.taewoo.crudapplication.service.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService, UserRepository userRepository) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardResponseDto> boards = boardService.getAllBoards();
        model.addAttribute("boards", boards);
        return "list";
    }

    @GetMapping("/write")
    public String writeBoard(Model model) {
        model.addAttribute("board", new BoardRequestDto());
        return "write";
    }

    @PostMapping("/add")
    public String addBoard(@ModelAttribute BoardRequestDto dto, Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        boardService.createBoard(dto, userId);
        return "redirect:/boards/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model, Authentication authentication) {
        BoardResponseDto board = boardService.getBoardById(id);
        if (!board.getUserId().equals(getCurrentUserId(authentication))) {
            throw new RuntimeException("No Authorized");
        }
        model.addAttribute("board", board);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute BoardRequestDto dto, Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        boardService.updateBoard(id, dto, userId);
        return "redirect:/boards/list";
    }


    @PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id, Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        boardService.deleteBoard(id, userId);
        return "redirect:/boards/list";
    }

    private Long getCurrentUserId(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getUser().getId();
    }
}
