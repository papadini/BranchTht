package com.test.branchtht.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RawUser {
    public String login;
    public String avatar_url;
    public String url;
    public String html_url;
    public String name;
    public String location;
    public String email;
    public Date created_at;
}
