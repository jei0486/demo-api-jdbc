package com.demo.api.controller;

import com.demo.api.model.Board;
import com.demo.api.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping("")
    private List<Board> boardList(@RequestParam(value="size", defaultValue = "10") int size ,
                                  @RequestParam(value="page", defaultValue = "1") int page,
                                  @RequestParam(value="sortColumn", defaultValue = "seq") String sortColumn,
                                  @RequestParam(value="sortOrder", defaultValue = "DESC") String sortOrder){
        return boardService.boardList(size,page,sortColumn,sortOrder);
    }

    @GetMapping("/search")
    private List<Board> boardSearch(@RequestParam(value="size", defaultValue = "10") int size ,
                                    @RequestParam(value="page", defaultValue = "1") int page,
                                    @RequestParam(value="sortColumn", defaultValue = "seq") String sortColumn,
                                    @RequestParam(value="sortOrder", defaultValue = "DESC") String sortOrder,
                                    @RequestParam(value="param") String param){
        return boardService.boardSearch(size,page,sortColumn,sortOrder,param);
    }

    @GetMapping("/{boardId}")
    private Board show(@PathVariable Long boardId) throws IllegalArgumentException {
        return boardService.show(boardId);
    }

    @PostMapping("")
    private Board save(@RequestBody Board board) {
        return boardService.save(board);
    }

    @DeleteMapping("/{boardId}")
    private void delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
    }

    @PutMapping("/{boardId}")
    private Board update(@PathVariable Long boardId, @RequestBody Board board) throws Exception {
        return boardService.update(boardId, board);
    }

}
