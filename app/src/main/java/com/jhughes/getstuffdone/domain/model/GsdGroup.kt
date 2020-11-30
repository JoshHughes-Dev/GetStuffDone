package com.jhughes.getstuffdone.domain.model

data class GsdGroup(val id: Int, val title: String, val tasks: List<GsdTask>)