package cn.edu.nciae.ustudy.service;

import cn.edu.nciae.ustudy.po.CourseComment;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author YeLi
 * @date 2020-09-06
 */
public interface ICourseCommentService extends IService<CourseComment> {

    IPage<CourseComment> pageByCourseId(Long id, Page<CourseComment> page);
}
