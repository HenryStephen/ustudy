package cn.edu.nciae.ustudy.service;

import cn.edu.nciae.ustudy.dto.CourseDTO;
import cn.edu.nciae.ustudy.po.Course;
import cn.edu.nciae.ustudy.po.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author YeLi
 * @date 2020-09-06
 */
public interface ICourseService extends IService<Course> {

    CourseDTO getCourse(Long id);

	List<CourseDescription> getDescription(Long courseId);

	void quitCourse(Long id, List<Long> courseIdList);

	List<Course> getExpiredCourse(Long userId);

	List<Course> getLearningCourse(Long userId);
}
