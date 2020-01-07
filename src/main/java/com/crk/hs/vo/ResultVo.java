package com.crk.hs.vo;

/**
 * 统一返回处理类
 * @param <T>
 */
/*http请求返回的最外层对象*/
public class ResultVo<T> {

    private Integer code;

    private String msg;

    private T data;

    private ResultVo(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private ResultVo(CodeMsg cm){
        if(cm==null){
            return;
        }else {
            this.code = cm.getCode();
            this.msg = cm.getMsg();
            this.data = null;
        }
    }

    public static <T> ResultVo<T> success(T data){
        return new ResultVo<T>(data);
    }

    public static <T> ResultVo<T> error(CodeMsg cm){
        return new ResultVo<T>(cm);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
