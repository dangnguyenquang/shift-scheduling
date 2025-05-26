package com.example.shift_scheduling.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LoaiCa {
    @JsonProperty("ca_sang")
    CASANG,

    @JsonProperty("ca_chieu")
    CACHIEU,

    @JsonProperty("ca_toi")
    CATOI,
}

