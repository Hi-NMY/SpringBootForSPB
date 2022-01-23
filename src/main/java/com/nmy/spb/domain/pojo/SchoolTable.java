package com.nmy.spb.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nmy
 * @title: SchoolTable
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolTable {

    private int class_id;
    private String class_name;
    private String class_room;
    private String class_teacher;
    private String class_date;
    private String class_num;
    private String class_time_start;
    private String class_time_over;

}
