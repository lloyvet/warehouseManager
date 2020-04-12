package com.lloyvet.business.vo;

import com.lloyvet.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class GoodsVo extends BaseVo {
    private Integer providerid;
    private String goodsname;
    private String size;
    private String productcode;
    private String promitcode;
    private String description;
}
