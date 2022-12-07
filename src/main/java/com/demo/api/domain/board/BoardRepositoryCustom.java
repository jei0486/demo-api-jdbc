package com.demo.api.domain.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<BoardEntity> find (Pageable pageable);
}
