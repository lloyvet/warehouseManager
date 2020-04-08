package com.lloyvet.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lloyvet.system.domain.Menu;

public interface MenuMapper extends BaseMapper<Menu> {
    Integer queryMenuMaxOrderNum();

    Integer countChildren(Integer id);
}