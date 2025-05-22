package com.example.shift_scheduling.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LoaiMon {
    @JsonProperty("mon_au")
    MON_AU,

    @JsonProperty("mon_han")
    MON_HAN,

    @JsonProperty("mon_nhat")
    MON_NHAT,

    @JsonProperty("mon_trung")
    MON_TRUNG,

    @JsonProperty("mon_viet")
    MON_VIET,

    @JsonProperty("mon_chay")
    MON_CHAY,

    @JsonProperty("mon_nuong")
    MON_NUONG,

    @JsonProperty("trang_mieng_au")
    TRANG_MIENG_AU,

    @JsonProperty("trang_mieng_a")
    TRANG_MIENG_A;
}

