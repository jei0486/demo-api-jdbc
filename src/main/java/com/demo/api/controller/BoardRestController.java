package com.demo.api.controller;

import com.demo.api.model.Board;
import com.demo.api.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping("/totalCount")
    private int boardTotalCont(){
        return boardService.totalCount();
    }

    @GetMapping("/count")
    private int countByParam(@RequestParam(value="size", defaultValue = "10") int size ,
                          @RequestParam(value="page", defaultValue = "0") int page,
                          @RequestParam(value="param", required = false) String param){
        return boardService.countByParam(page,size,param);
    }

    @GetMapping("")
    private List<Board> boardList(@PageableDefault(page = 0,size = 10, sort = {"seq"}, direction = Sort.Direction.DESC) Pageable pageable){
        return boardService.boardList(pageable);
    }

    @GetMapping("/search")
    private List<Board> boardSearch(@PageableDefault(size = 10, page = 0, sort = {"seq"}, direction = Sort.Direction.DESC) Pageable pageable,
                                    @RequestParam(value="param") String param){
        return boardService.boardSearch(pageable,param);
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
