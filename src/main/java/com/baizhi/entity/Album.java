package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Serializable {
    @Id
    private String id;
    private String title;
    private String cover;
    private Integer count;
    private Double score;
    private String author;
    private String broadcast;
    private String brief;
    private Date createDate;

}
