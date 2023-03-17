package cn.edu.nciae.ustudy.service;

import cn.edu.nciae.ustudy.po.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author YeLi
 * @date 2020-09-06
 */
public interface IFileService extends IService<File> {

    String uploadFile(MultipartFile file, File fileInfo) throws IOException;

    String uploadFiles(List<MultipartFile> files, List<File> infos) throws IOException;

    String generatePath(String id, String type);

    Resource downloadByFileId(String courseId, String id) throws IOException;

    Resource downloadByFileName(String fileType, String fileName) throws IOException;
}
