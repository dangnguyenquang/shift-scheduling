package com.example.shift_scheduling.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LoaiNV {
    @JsonProperty("dau_bep")
    DAUBEP,

    @JsonProperty("phuc_vu")
    PHUCVU,

    @JsonProperty("le_tan")
    LETAN,
}

