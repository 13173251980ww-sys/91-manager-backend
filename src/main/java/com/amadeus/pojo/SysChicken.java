package com.amadeus.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "ck_id",
        "ck_nick",
        "ck_times",
        "ck_rating",
        "ck_per",
        "ck_property",
        "ck_data_height",
        "ck_data_weight",
        "ck_data_length",
        "ck_data_breast"
})

@Data
public class SysChicken {
    private Integer ck_id;
    private String ck_nick;
    private String ck_times;
    private String ck_rating;
    private String ck_per;
    private String ck_property;
    private String ck_data_height;
    private String ck_data_weight;
    private String ck_data_length;
    private String ck_data_breast;
}