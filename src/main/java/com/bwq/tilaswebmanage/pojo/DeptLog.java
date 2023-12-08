package com.bwq.tilaswebmanage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptLog {
    private int id;
    private LocalDateTime createTime;
    private String description;
}
