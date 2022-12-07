package com.demo.api.service;

import com.demo.api.domain.board.BoardRepository;
import com.demo.api.domain.board.BoardRepositoryCustomImpl;
import com.demo.api.model.Board;
import com.demo.api.domain.board.BoardEntity;
import com.demo.api.domain.board.BoardRepositoryCustom;
import com.demo.api.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> boardList(int size,int page,String sortColumn,String sortOrder) {
        int realPage = page <= 0 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(realPage, size, Sort.by(Sort.Direction.fromString(sortOrder), sortColumn));
        Page<BoardEntity> boardEntityList = boardRepository.findAll(pageable);
        List<Board> collect = boardEntityList.stream().map(boardEntity -> ModelMapperUtil.map(boardEntity,Board.class)).collect(Collectors.toList());
        return collect;
    }


    // search
    public List<Board> boardSearch(int size,int page,String sortColumn,String sortOrder,String param) {

        int realPage = page <= 0 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(realPage, size, Sort.by(Sort.Direction.fromString(sortOrder), sortColumn));
        Page<BoardEntity> boardEntityPageList = boardRepository.find(pageable);
        List<Board> boardList = boardEntityPageList.stream().map(boardEntity -> ModelMapperUtil.map(boardEntity,Board.class)).collect(Collectors.toList());
        return boardList;
    }

    public Board show(Long boardId) throws IllegalArgumentException{
        BoardEntity old = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("no such data"));

        old.setHits(old.getHits()+1);
        BoardEntity boardEntity = boardRepository.save(old);
        return ModelMapperUtil.map(boardEntity,Board.class);
    }

    public Board save(Board board){
        BoardEntity boardEntity = boardRepository.save(BoardEntity.builder()
                .createdId(board.getCreatedId())
                .subject(board.getSubject())
                .content(board.getContent())
                .hits(0)
                .build());
        return ModelMapperUtil.map(boardEntity,Board.class);
    }

    public void delete(Long boardId){
        boardRepository.deleteById(boardId);
    }

    public Board update(Long boardId,Board board) throws Exception {
        BoardEntity old = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception("board id is not exist"));

        old.setSubject(board.getSubject());
        old.setContent(board.getContent());
        old.setModifiedId(board.getModifiedId());
        BoardEntity boardEntity = boardRepository.save(old);
        return ModelMapperUtil.map(boardEntity,Board.class);
    }

}
