package com.lloyvet.business.vo;

import com.lloyvet.system.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: 0812erp
 * @author: 雷哥
 * @create: 2020-01-11 17:10
 **/

@Data
@EqualsAndHashCode(callSuper=false)
public class SalesVo extends BaseVo {

    private Integer customerid;

    private Integer goodsid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;


}
