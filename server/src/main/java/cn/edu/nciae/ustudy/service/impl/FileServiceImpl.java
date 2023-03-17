package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.exception.ServiceException;
import cn.edu.nciae.ustudy.mapper.FileMapper;
import cn.edu.nciae.ustudy.po.File;
import cn.edu.nciae.ustudy.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private static final String SEPARATOR = java.io.File.separator;

    private static final String PROTOCOL = "file://";

    @Value("${ustudy.file.location}")
    private String location;

    @Override
    public String uploadFile(MultipartFile file, File info) throws IOException {
        if (info.getUserId() == null || StringUtils.isBlank(info.getType())) {
            throw new ServiceException("No file included in the request.");
        }

        // generate new path likes "/ustudy/avatar/9bfcbab6-cd14-4f75-8667-f4298afc8e5e.jpg"
        String newName = info.getId() + "." + StringUtils.substringAfterLast(info.getName(), ".");
        String path = generatePath(newName, info.getType());

        // mkdirs and save
        java.io.File folder = new java.io.File(StringUtils.substringBeforeLast(path, SEPARATOR));
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
        Files.write(Paths.get(path), file.getBytes());

        // save information to database
        save(info);
        logger.info("File saved success. path: {}", path);
        return newName;
    }

    @Override
    public String uploadFiles(List<MultipartFile> files, List<File> infos) throws IOException {
        String id = null;
        for (int i = 0; i < files.size(); i++) {
            id = uploadFile(files.get(i), infos.get(i));
        }
        return id;
    }

    @Override
    @PreAuthorize("@orderService.checkOwnerPermission(authentication, #courseId)")
    public Resource downloadByFileId(String courseId, String id) throws IOException {
        File file = baseMapper.selectById(id);
        if (!StringUtils.equals(courseId, file.getCourseId().toString())) {
            throw new ServiceException("This file doesn't belong the course.");
        }
        String newName = file.getId() + "." + StringUtils.substringAfterLast(file.getName(), ".");
        String path = PROTOCOL + generatePath(newName, file.getType());
        Resource resource = new UrlResource(path);
        if (!resource.exists()) {
            throw new IOException("File not found: " + id);
        }
        return resource;
    }

    @Override
    public Resource downloadByFileName(String fileType, String fileName) throws IOException {
        String path = PROTOCOL + generatePath(fileName, fileType);
        Resource resource = new UrlResource(path);
        if (!resource.exists()) {
            throw new IOException("File not found: " + path);
        }
        return resource;
    }

    /**
     * The generated regular for file path and name.
     */
    @Override
    public String generatePath(String name, String type) {
        if (StringUtils.endsWith(location, SEPARATOR)) {
            location = location.substring(0, location.length() - SEPARATOR.length());
        }
        String path = StringUtils.join(Arrays.asList(location, type, name), SEPARATOR);
        logger.debug("Generated path: [{}]", path);
        return path;
    }

}
