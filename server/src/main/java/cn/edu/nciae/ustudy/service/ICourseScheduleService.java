package cn.edu.nciae.ustudy.service;

import cn.edu.nciae.ustudy.po.CourseSchedule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author YeLi
 * @date 2020-09-06
 */
public interface ICourseScheduleService extends IService<CourseSchedule> {

    List<CourseSchedule> getByCourseId(Long courseId);
}
