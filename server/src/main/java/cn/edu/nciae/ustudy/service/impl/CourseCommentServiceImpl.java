package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.po.CourseComment;
import cn.edu.nciae.ustudy.mapper.CourseCommentMapper;
import cn.edu.nciae.ustudy.service.ICourseCommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Service
public class CourseCommentServiceImpl extends ServiceImpl<CourseCommentMapper, CourseComment> implements ICourseCommentService {

    @Override
    public IPage<CourseComment> pageByCourseId(Long id, Page<CourseComment> page) {
        return lambdaQuery()
                .eq(CourseComment::getCourseId, id)
                .eq(CourseComment::getDeleted, false)
                .page(page);
    }
}
