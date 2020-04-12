package com.lloyvet.business.vo;

import com.lloyvet.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ProviderVo extends BaseVo {
    private String providername;
    private String phone;
    private String connectionperson;
}
