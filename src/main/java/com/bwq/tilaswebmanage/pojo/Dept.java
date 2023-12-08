package com.bwq.tilaswebmanage.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 部门实体类,@里面的会自动创建一些函数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Integer id; //ID
    private String name; //部门名称
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
