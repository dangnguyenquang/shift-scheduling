package com.example.shift_scheduling.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TrangThaiXepCa {
    @JsonProperty("chua_xep")
    CHUAXEP,

    @JsonProperty("thanh_cong")
    THANHCONG,

    @JsonProperty("that_bai")
    THATBAI
}
