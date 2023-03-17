package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.UstudyApplication;
import cn.edu.nciae.ustudy.po.Course;
import cn.edu.nciae.ustudy.po.CourseDescription;
import cn.edu.nciae.ustudy.service.ICourseDescriptionService;
import cn.edu.nciae.ustudy.service.ICourseService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author zhang
 * @date 2020/9/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UstudyApplication.class)
@WebAppConfiguration
public class CourseServiceImplTest {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseDescriptionService courseDescriptionService;

    private Long courseId;

    private Long courseDescriptionId;

    @Before
    public void testSaveCourse() {
        Course course = new Course();
        course.setName("测试课1");
        course.setPreviewPicture("链接1");
        course.setFree(true);
        course.setOriginalPrice(new BigDecimal("100.0"));
        course.setPresentPrice(BigDecimal.ZERO);
        course.setCategoryId(1L);
        course.setSubjectId(1001L);
        course.setTeachingMethods("1");
        course.setPublishedTime(LocalDate.now());
        course.setStudentsNumber(100);
        course.setValidDays(112);
        course.setClassHour(80);
        boolean res = courseService.save(course);
        Assert.assertTrue(res);
        courseId = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setCourseId(courseId);
        courseDescription.setNumber(1);
        courseDescription.setContent("content 1");
        courseDescription.setType(1);
        res = courseDescriptionService.save(courseDescription);
        Assert.assertTrue(res);
        courseDescriptionId = courseDescription.getId();
    }

    @Test
    public void testGetCourse() {
        Assert.assertNotNull(courseService.getCourse(courseId));
    }

    @Test
    public void testGetDescription() {
        Assert.assertNotNull(courseService.getDescription(courseId));
    }

    @After
    public void testRemoveCourse() {
        Assert.assertTrue(courseService.removeById(courseId));
        Assert.assertTrue(courseDescriptionService.removeById(courseDescriptionId));
    }
}