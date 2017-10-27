package com.mmall.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author alone95
 * @date 2017/10/26
 */
@Getter
@Setter
public class TestVo {
    @NotBlank
    private  String msg;

    @NotNull(message = "id不能为空")
    @Max(value = 10 ,message = "id不能大于10")
    private  Integer id;


    private List<String> str;
}
