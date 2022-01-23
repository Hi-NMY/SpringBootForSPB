package com.nmy.spb.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nmy
 * @title: Sign
 * @date 2022-01-22 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sign{

    public String user_account;
    public int sign_coin;
    public int sign_right;
    public String sign_day;
    public String sign_star_badge;
    public String sign_task_badge;
    public String sign_like_badge;
    public int sign_ct_badge;

}
