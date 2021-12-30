package com.njupt.system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Elaine Huang
 * @date 2021/12/25 9:35 PM
 * @signature Do it while you can!
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddInformation {
    private Information information;
    private List<String> objects;
}
