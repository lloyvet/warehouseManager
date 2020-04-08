package com.lloyvet.system.service;

import com.lloyvet.system.common.DataGridView;
import com.lloyvet.system.domain.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lloyvet.system.vo.NoticeVo;

public interface NoticeService extends IService<Notice>{


    DataGridView queryAllNotice(NoticeVo noticeVo);
}
