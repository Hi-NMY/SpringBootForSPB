package com.nmy.spb.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: Students
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Students {
    private int id;
    private String stu_name;
    private String stu_sex;
    private int stu_year;
    private String stu_department;
    private String stu_major;
    private int stu_class;
    private String stu_num;

}
