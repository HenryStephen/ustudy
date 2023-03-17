package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.po.CourseSchedule;
import cn.edu.nciae.ustudy.mapper.CourseScheduleMapper;
import cn.edu.nciae.ustudy.service.ICourseScheduleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Service
public class CourseScheduleServiceImpl extends ServiceImpl<CourseScheduleMapper, CourseSchedule> implements ICourseScheduleService {

    @Override
    public List<CourseSchedule> getByCourseId(Long courseId) {
        return list(new LambdaQueryWrapper<CourseSchedule>()
                .eq(CourseSchedule::getCourseId, courseId)
                .eq(CourseSchedule::getDeleted, false)
        );
    }
}
