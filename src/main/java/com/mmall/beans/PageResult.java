package com.mmall.beans;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author alone95
 * @date 2017/11/2
 */
@Getter
@Setter
@ToString
@Builder
public class PageResult<T> {

    public List<T> data = Lists.newArrayList();

    private int total =0;
}
