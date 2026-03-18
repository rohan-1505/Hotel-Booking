package com.example.hotel.advice;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class APIResponse<T> {
    public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public APIError getError() {
		return error;
	}

	public void setError(APIError error) {
		this.error = error;
	}

	private LocalDateTime time;
    private T data;
    private APIError error;

    public APIResponse(T data) {
        this();
        this.data = data;
    }

    public APIResponse() {
        this.time = LocalDateTime.now();
    }

    public APIResponse(APIError error) {
        this();
        this.error = error;
    }
}
