package com.njupt.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Elaine Huang
 * @date 2021/12/20 2:58 PM
 * @signature Do it while you can!
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInModel {

    private String userId;

    private String password;

    private int code;
}
