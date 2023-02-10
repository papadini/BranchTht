package com.test.branchtht.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormattedUserDto {
    String user_name;
    String display_name;
    String avatar;
    String geo_location;
    String email;
    String url;
    String created_at;
    List<RawUserRepo> repos;
}
