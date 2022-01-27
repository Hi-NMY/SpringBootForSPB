package com.nmy.spb.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author nmy
 * @title: Diary
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diary {

    public int id;
    public String user_account;
    public String dia_date;
    public int dia_weather;
    public String dia_message;
    public String dia_image;
}
