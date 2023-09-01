package com.gery.collection.controller.list;

import com.gery.collection.model.StrategyObjectPreviewVo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @program: gery-demo-2023
 * @ClassName OrderListController
 * @description:
 * @author: yaowenhua
 * @create: 2023-08-31 14:06
 * @Version 1.0
 **/
public class OrderListController {
    public static void main(String[] args) {
        List<StrategyObjectPreviewVo> strategyObjectPreviewVoList=new ArrayList<>();
        Comparator<StrategyObjectPreviewVo> customComparator = Comparator.comparing(StrategyObjectPreviewVo::getFrequencyValue).reversed()
                .thenComparing((previewVo) -> {
                    if (Objects.equals(previewVo.getAirSeason(), "SeasonalRequirement.COOLINGSEASON.getDesc()")) {
                        return 0;
                    } else if (Objects.equals(previewVo.getAirSeason(), "SeasonalRequirement.HEATINGSEASON.getDesc()")) {
                        return 1;
                    } else if (Objects.equals(previewVo.getAirSeason(), "SeasonalRequirement.TRANSITIONSEASON.getDesc()")) {
                        return 2;
                    } else if (Objects.equals(previewVo.getAirSeason(), "SeasonalRequirement.NOREQUIREMENT.getDesc()")) {
                        return 3;
                    }
                    return null;
                }).thenComparing((previewVo) -> {
                    if (Objects.equals(previewVo.getExecutionPeriod(), "TimeWorkingRequirement.BUSINESSHOURS.getDesc()")) {
                        return 4;
                    } else if (Objects.equals(previewVo.getExecutionPeriod(), "TimeWorkingRequirement.NONBUSINESSHOURS.getDesc()")) {
                        return 5;
                    } else if (Objects.equals(previewVo.getExecutionPeriod(), "TimeWorkingRequirement.SPECIFICTIME.getDesc()")) {
                        return 6;
                    } else if (Objects.equals(previewVo.getExecutionPeriod(), "TimeWorkingRequirement.NOREQUIREMENT.getDesc()")) {
                        return 7;
                    }
                    return null;
                });
        strategyObjectPreviewVoList.sort(customComparator);
    }
}
