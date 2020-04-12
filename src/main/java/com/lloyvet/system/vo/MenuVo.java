package com.lloyvet.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuVo extends BaseVo {
    Integer hasPermission; //0不要权限
}
