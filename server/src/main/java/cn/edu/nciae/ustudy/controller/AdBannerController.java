package cn.edu.nciae.ustudy.controller;


import cn.edu.nciae.ustudy.po.AdBanner;
import cn.edu.nciae.ustudy.service.IAdBannerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YeLi
 * @author zhang
 */
@Api
@RestController
@RequestMapping("/ad/banner")
public class AdBannerController {

	@Autowired
	private IAdBannerService adBannerService;

	@GetMapping
	@ApiOperation("分页查询banner广告")
	public IPage<AdBanner> pages(Page<AdBanner> page) {
		return adBannerService.page(page);
	}

}

