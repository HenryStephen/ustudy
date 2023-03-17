package cn.edu.nciae.ustudy.service;

import cn.edu.nciae.ustudy.po.CourseCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 * @author YeLi
 * @date 2020-09-06
 */
public interface ICourseCategoryService extends IService<CourseCategory> {

	List<CourseCategory> getByCourseId(Long id);

	List<CourseCategory> getByCourse();
}
