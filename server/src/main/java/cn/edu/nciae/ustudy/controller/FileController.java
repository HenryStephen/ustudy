package cn.edu.nciae.ustudy.controller;


import cn.edu.nciae.ustudy.annotation.IgnoreResponseAdvice;
import cn.edu.nciae.ustudy.config.Result;
import cn.edu.nciae.ustudy.po.File;
import cn.edu.nciae.ustudy.service.IFileService;
import cn.edu.nciae.ustudy.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author YeLi
 * @date 2020-09-06
 */
@Api
@RestController
@IgnoreResponseAdvice
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IFileService fileService;

    @Autowired
    private IUserService userService;

//    @PostMapping("/file")
//    @ApiOperation("上传文件接口，返回文件id（上传多文件时返回最后一个）")
//    public Result<String> upload(
//            Authentication authentication,
//            @RequestParam List<MultipartFile> files,
//            @RequestParam String type,
//            @RequestParam Long courseId,
//            @RequestParam(required = false) Long scheduleId
//    ) throws IOException {
//        if (CollectionUtils.isEmpty(files)) {
//            throw new ServiceException("Uploading failed.Please try again.");
//        }
//        User user = userService.getByPhone(authentication.getName());
//        List<File> infos = new ArrayList<>();
//        for (MultipartFile file: files) {
//            infos.add(new File(UUID.randomUUID().toString(), courseId, user.getId(), scheduleId,
//                    file.getOriginalFilename(), type, LocalDateTime.now(), file.getSize()));
//        }
//        return Result.success(fileService.uploadFiles(files, infos));
//    }

//    @GetMapping("/course/{courseId}/file/{fileName:.+}")
//    @ApiOperation("通过课程id和文件名下载")
//    public ResponseEntity<Resource> downloadCourseFile(@PathVariable String courseId, @PathVariable String fileName,
//                                                       HttpServletRequest request) throws IOException {
//        String fileId = StringUtils.substringBefore(fileName, ".");
//        Resource resource = fileService.downloadByFileId(courseId, fileId);
//        return getResult(request, resource);
//    }

    @GetMapping("/file/{fileType}/{fileName:.+}")
    @ApiOperation("通过文件类型和文件名下载文件")
    public ResponseEntity<Resource> downloadStaticResources(@PathVariable String fileType,
            @PathVariable String fileName, HttpServletRequest request) throws IOException{
        return getResult(request, fileService.downloadByFileName(fileType, fileName));
    }


    private ResponseEntity<Resource> getResult(HttpServletRequest request, Resource resource)
            throws UnsupportedEncodingException {
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.debug("Could not determine file type.");
        }

        contentType = contentType == null ? "application/octet-stream" : contentType;
        String filename = resource.getFilename() == null ? "unknown_name" : resource.getFilename();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + URLEncoder.encode(filename, String.valueOf(StandardCharsets.UTF_8))
                                + "\"")
                .body(resource);
    }

    @GetMapping("/course/{courseId}/{scheduleId}/file")
    public Result<File> getFiles(@PathVariable Long courseId, @PathVariable Long scheduleId) {
        File file = fileService.lambdaQuery()
                .eq(File::getCourseId, courseId)
                .eq(File::getScheduleId, scheduleId)
                .one();
        logger.info("{}", file);
        return Result.success(file);
    }


}

