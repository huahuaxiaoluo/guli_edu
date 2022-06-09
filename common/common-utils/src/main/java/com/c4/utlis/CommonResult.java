package com.c4.utlis;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
/*
统一返回json格式
 */
@Data
@Accessors(chain = true)
public class CommonResult {
    private Boolean isSuccess;
    private Integer code;
    private String msg;
    private Map<String,Object> data = new HashMap<>();
    //单例模式
    private CommonResult(){}

    public static CommonResult success(){
        CommonResult result = new CommonResult();
        result.setIsSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMsg("成功");
        return result;
    }
    public static CommonResult error(){
        CommonResult result = new CommonResult();
        result.setIsSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMsg("错误");
        return  result;
    }
    public CommonResult data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}
