package com.demo.api.domain.board;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.PastOrPresent;
import java.time.Instant;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

    @Id
    private Long seq;
    private String subject;
    private String content;

    @Builder.Default
    @Column
    private Integer hits = 0; //default 0

    //@Column("created_id")
    private String createdId;

    private String modifiedId;

    // Entity가 생성되어 저장될 때 시간이 자동 저장
    @CreatedDate
    private LocalDateTime createdAt;

    // 조회한 Entity의 값을 변경할 때 시간이 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}
