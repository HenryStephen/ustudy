package cn.edu.nciae.ustudy.controller;


import cn.edu.nciae.ustudy.dto.CourseDTO;
import cn.edu.nciae.ustudy.dto.CourseSearchDTO;
import cn.edu.nciae.ustudy.po.*;
import cn.edu.nciae.ustudy.service.ICourseCommentService;
import cn.edu.nciae.ustudy.service.ICourseScheduleService;
import cn.edu.nciae.ustudy.service.ICourseService;
import cn.edu.nciae.ustudy.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author YeLi
 * @author zhang
 */
@Api
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseCommentService courseCommentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseScheduleService courseScheduleService;

    @GetMapping
    @ApiOperation("分页查询、排序、搜索")
    public IPage<Course> pages(Page<Course> page, CourseSearchDTO courseSearchDTO) {
        LambdaQueryWrapper<Course> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(courseSearchDTO.getName()), Course::getName, courseSearchDTO.getName())
                .eq(courseSearchDTO.getCategoryId() != null, Course::getCategoryId, courseSearchDTO.getCategoryId())
                .eq(courseSearchDTO.getSubjectId() != null, Course::getSubjectId, courseSearchDTO.getSubjectId())
                .eq(courseSearchDTO.getFree() != null, Course::getFree, courseSearchDTO.getFree())
                .ge(courseSearchDTO.getStartPrice() != null && courseSearchDTO.getStartPrice().compareTo(BigDecimal.ZERO) > 0,
                        Course::getPresentPrice, courseSearchDTO.getStartPrice())
                .le(courseSearchDTO.getEndPrice() != null, Course::getPresentPrice, courseSearchDTO.getEndPrice())
                .like(StringUtils.isNotEmpty(courseSearchDTO.getTeachingMethods()),
                        Course::getTeachingMethods, courseSearchDTO.getTeachingMethods());
        return courseService.page(page, queryWrapper);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取课程详情信息")
    public CourseDTO get(@PathVariable Long id) {
        return courseService.getCourse(id);
    }

    @GetMapping("/{courseId}/description")
    @ApiOperation("获取课程介绍")
    public List<CourseDescription> getDescription(@PathVariable Long courseId) {
        return courseService.getDescription(courseId);
    }

    @GetMapping("/{id}/comment")
    @ApiOperation("获取课程评价")
    public IPage<CourseComment> commentPage(@PathVariable Long id, Page<CourseComment> page) {
        return courseCommentService.pageByCourseId(id, page);
    }

    @PostMapping("/{id}/comment")
    @ApiOperation("评价课程")
    public void commentCourse(@PathVariable Long id, Authentication authentication, CourseComment comment) {
        User user = userService.getByPhone(authentication.getName());
        comment.setCourseId(id);
        comment.setUserId(user.getId());
        comment.setTime(LocalDateTime.now());
        courseCommentService.save(comment);
    }

    @GetMapping("/{courseId}/schedule")
    @ApiOperation("课时安排")
    public List<CourseSchedule> schedules(@PathVariable Long courseId) {
        return courseScheduleService.getByCourseId(courseId);
    }

    @GetMapping("/learning")
    @ApiOperation("查看用户正在学习的课程")
    public List<Course> getLearning(Authentication authentication){
        User user = userService.getByPhone(authentication.getName());
        return courseService.getLearningCourse(user.getId());
    }

    @GetMapping("/expired")
    @ApiOperation("查看用户过期的课程")
    public List<Course> getExpired(Authentication authentication){
        User user = userService.getByPhone(authentication.getName());
        return courseService.getExpiredCourse(user.getId());
    }

    @DeleteMapping("/learning")
    @ApiOperation("用户退出课程")
    public void quitCourse(Authentication authentication, @RequestParam("courseIdList") List<Long> courseIdList){
        User user = userService.getByPhone(authentication.getName());
        courseService.quitCourse(user.getId(), courseIdList);
    }
}

