package com.example.savvy.services.stats;

import com.example.savvy.dto.GraphDTO;
import com.example.savvy.dto.StatsDTO;

public interface StatsService {

    GraphDTO getChartData();
    StatsDTO getStats();
}
