package cn.edu.nciae.ustudy.mapper;

import cn.edu.nciae.ustudy.po.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YeLi
 * @author zhang
 */
@Mapper
@Repository
public interface CourseMapper extends BaseMapper<Course> {

	void quit(@Param("userId") Long userId, @Param("courseIdList") List<Long> courseIdList);

	List<Course> selectLearningCourse(@Param("userId") Long userId);

	List<Course> selectExpiredCourse(@Param("userId") Long userId);
}
