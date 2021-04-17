package com.gotta_watch_them_all.app.infrastructure.dataprovider.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user_work")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class UserWorkEntity implements Serializable {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "work_id")
    private Long workId;
}
