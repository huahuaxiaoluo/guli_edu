package com.c4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.c4.bean.EduTeacher;
import com.c4.mapper.EduTeacherMapper;
import com.c4.service.EduTeacherService;
import org.springframework.stereotype.Service;

/**
* @author sj
* @description 针对表【edu_teacher(讲师)】的数据库操作Service实现
* @createDate 2022-06-07 22:35:40
*/
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
    implements EduTeacherService {
}




