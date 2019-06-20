package com.innowise.coordination.entity;

import lombok.Data;

@Data
public class Response {

    private String response;

    public Response(String response) {
        this.response = response;
    }
}
