package com.lloyvet.business.vo;

import com.lloyvet.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CustomerVo extends BaseVo {
    private String customername;
    private String phone;
    private String connectionPerson;
}
