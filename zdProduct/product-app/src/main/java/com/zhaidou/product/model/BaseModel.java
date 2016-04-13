package com.zhaidou.product.model;


public class BaseModel<T> {
	private int status;
	private String error_msg;
	private T data;

	public boolean isSuccess()
	{
		return 0 == status;
	}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
	
}
