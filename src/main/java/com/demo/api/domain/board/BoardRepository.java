package com.demo.api.domain.board;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BoardRepository extends PagingAndSortingRepository<BoardEntity,Long> ,BoardRepositoryCustom{
}
