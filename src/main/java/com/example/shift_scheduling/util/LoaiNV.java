package com.example.shift_scheduling.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum LoaiNV {
    @JsonProperty("dau_bep")
    DAU_BEP,

    @JsonProperty("phuc_vu")
    PHUC_VU,

    @JsonProperty("le_tan")
    LE_TAN,
}

