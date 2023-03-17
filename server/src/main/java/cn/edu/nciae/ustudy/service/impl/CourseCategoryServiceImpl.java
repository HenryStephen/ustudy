package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.mapper.CourseCategoryMapper;
import cn.edu.nciae.ustudy.po.CourseCategory;
import cn.edu.nciae.ustudy.service.ICourseCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author YeLi
 * @author zhang
 */
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements ICourseCategoryService {

	@Override
	public List<CourseCategory> getByCourseId(Long id) {
		return lambdaQuery()
				.eq(CourseCategory::getParentId, id)
				.eq(CourseCategory::getDeleted, false)
				.list();
	}

	@Override
	public List<CourseCategory> getByCourse() {
		return lambdaQuery().eq(CourseCategory::getDeleted,false).list();
	}
}
