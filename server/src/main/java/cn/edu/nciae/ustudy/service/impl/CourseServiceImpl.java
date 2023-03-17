package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.dto.CourseDTO;
import cn.edu.nciae.ustudy.exception.ServiceException;
import cn.edu.nciae.ustudy.mapper.CourseMapper;
import cn.edu.nciae.ustudy.po.Course;
import cn.edu.nciae.ustudy.po.CourseCategory;
import cn.edu.nciae.ustudy.po.CourseDescription;
import cn.edu.nciae.ustudy.service.ICourseCategoryService;
import cn.edu.nciae.ustudy.service.ICourseDescriptionService;
import cn.edu.nciae.ustudy.service.ICourseService;
import cn.edu.nciae.ustudy.utils.ConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private ICourseCategoryService courseCategoryService;

    @Autowired
    private ICourseDescriptionService descriptionService;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public CourseDTO getCourse(Long id) {
        Course course = getById(id);
        if (Objects.isNull(course) || course.getDeleted()) {
            throw new ServiceException("Course not found");
        }
        CourseDTO res = ConvertUtils.from(course, CourseDTO.class);
        res.setCourseCategories(new ArrayList<>());
        CourseCategory category = courseCategoryService.getOne(new LambdaQueryWrapper<CourseCategory>()
                .eq(CourseCategory::getId, course.getCategoryId())
                .eq(CourseCategory::getDeleted, false
        ));
        res.getCourseCategories().add(category);
        if (Objects.nonNull(category)) {
            res.getCourseCategories().add(courseCategoryService.getById(course.getSubjectId()));
        }
        res.setCourseDescriptions(descriptionService.list(new LambdaQueryWrapper<CourseDescription>()
                .eq(CourseDescription::getCourseId, course.getId())
                .eq(CourseDescription::getDeleted, false
        )));
        // TODO Use xml can reduce time consumed.
        return res;
    }

    @Override
    public List<CourseDescription> getDescription(Long courseId) {
        return descriptionService.list(new LambdaQueryWrapper<CourseDescription>()
                .eq(CourseDescription::getCourseId, courseId)
                .eq(CourseDescription::getDeleted, false)
        );
    }

    @Override
    public void quitCourse(Long userId, List<Long> courseIdList) {
        courseMapper.quit(userId, courseIdList);
    }

    @Override
    public List<Course> getExpiredCourse(Long userId) {
        return courseMapper.selectExpiredCourse(userId);
    }

    @Override
    public List<Course> getLearningCourse(Long userId) {
        return courseMapper.selectLearningCourse(userId);
    }
}
