package com.gery.collection.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "exeStrategyObjectPreviewVo", description = "策略对象预览")
public class StrategyObjectPreviewVo implements Serializable {

    @ApiModelProperty(value = "策略名称")
    private String strategyName;


    @ApiModelProperty(value = "执行频次")
    private String executionFrequency;
    @ApiModelProperty(value = "执行频率 ")
    private BigDecimal frequencyValue;

    @ApiModelProperty(value = "适用空调季节")
    private String airSeason;

    @ApiModelProperty(value = "执行时段")
    private String executionPeriod;

    @ApiModelProperty(value = "是否外委")
    private String isOutsource;

    @ApiModelProperty(value = "对象集合")
    private List<ObjectType> objectTypes;

    @ApiModel(description = "SOP对象信息")
    @Data
    public static class ObjectType implements Serializable {

        @ApiModelProperty(value = "对象类型名称")
        private String objectTypeName;

        @ApiModelProperty(value = "对象类型编码")
        private String objectTypeCode;

        @ApiModelProperty(value = "对象 ID集合")
        private List<String> objectIds;

        @ApiModelProperty(value = "策略事项信息集合")
        private List<StrategyItem> strategyItems;
    }


    @ApiModel(description = "策略事项信息")
    @Data
    public static class StrategyItem implements Serializable {

        @ApiModelProperty(value = "策略事项 ID")
        private Long strategyItemId;

        @ApiModelProperty(value = "策略事项名称")
        private String strategyItemName;

        @ApiModelProperty(value = "小红点标识")
        private boolean redDotFlag = false;


    }
}



