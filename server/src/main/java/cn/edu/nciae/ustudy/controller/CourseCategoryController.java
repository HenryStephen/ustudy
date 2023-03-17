package cn.edu.nciae.ustudy.controller;


import cn.edu.nciae.ustudy.po.CourseCategory;
import cn.edu.nciae.ustudy.service.ICourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YeLi
 * @author zhang
 */
@RestController
@RequestMapping("/course/category")
public class CourseCategoryController {

	@Autowired
	private ICourseCategoryService courseCategoryService;

	/**
	 * 根据父id查找子id
	 */
	@GetMapping("/{id}")
	public List<CourseCategory> getCategoryById(@PathVariable Long id) {
		return courseCategoryService.getByCourseId(id);
	}

	@GetMapping
	public List<CourseCategory> getCategories(){
		return courseCategoryService.getByCourse();
	}
}
