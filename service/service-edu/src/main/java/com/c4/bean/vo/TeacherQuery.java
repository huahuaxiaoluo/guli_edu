package com.c4.bean.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeacherQuery {

    private String name;

    private Integer level;

    private String begin;

    private String end;
}
