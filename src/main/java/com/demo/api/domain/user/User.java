package com.demo.api.domain.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.core.Ordered;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.conversion.MutableAggregateChange;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("USER")
@Builder
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class User {

    @Id
    private String id;

    @Column("LOGIN_ID")
    private String loginId;

    @Column("NAME")
    private String name;

    @Column("STATE")
    private UserState state;

    private String password;

    @Builder.Default
    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    public void lock() {
        this.state = UserState.LOCKED;
    }

    public void delete() {
        this.state = UserState.DELETED;
    }

}
