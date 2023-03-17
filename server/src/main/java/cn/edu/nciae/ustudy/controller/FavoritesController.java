package cn.edu.nciae.ustudy.controller;


import cn.edu.nciae.ustudy.dto.CourseDTO;
import cn.edu.nciae.ustudy.dto.UserFavoriteDTO;
import cn.edu.nciae.ustudy.po.Course;
import cn.edu.nciae.ustudy.po.Favorites;
import cn.edu.nciae.ustudy.po.User;
import cn.edu.nciae.ustudy.service.ICourseService;
import cn.edu.nciae.ustudy.service.IFavoritesService;
import cn.edu.nciae.ustudy.service.IUserService;
import cn.edu.nciae.ustudy.utils.ConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YeLi
 * @author zhang
 */
@Api
@RestController
public class FavoritesController {

	@Autowired
	private IFavoritesService favoritesService;

	@Autowired
	private ICourseService courseService;

	@Autowired
	private IUserService userService;

	@ApiOperation("根据用户id查询购物车")
	@GetMapping("/cart")
	public List<UserFavoriteDTO> getCart(Authentication authentication){
		String userPhone = authentication.getName();
		User user = userService.getByPhone(userPhone);
		return getFavoriteAndCart(user.getId(),0);
	}

	@ApiOperation("根据用户id查询收藏车")
	@GetMapping("/favorite")
	public List<UserFavoriteDTO> getFavorite(Authentication authentication){
		String userPhone = authentication.getName();
		User user = userService.getByPhone(userPhone);
		return getFavoriteAndCart(user.getId(),1);
	}

	@ApiOperation("查询（抽象方法）")
	public List<UserFavoriteDTO> getFavoriteAndCart(@PathVariable Long userId, @PathVariable Integer type) {
		List<UserFavoriteDTO> userFavoriteDTOList = new ArrayList<>();
		List<Favorites> favoritesList = favoritesService.list(new LambdaQueryWrapper<Favorites>()
				.eq(Favorites::getUserId, userId)
				.eq(Favorites::getType, type)
				.eq(Favorites::getDeleted, false));
		for (Favorites favorites : favoritesList) {
			CourseDTO courseDTO = courseService.getCourse(userId);
			UserFavoriteDTO userFavoriteDTO = ConvertUtils.from(favorites, UserFavoriteDTO.class);
			userFavoriteDTO.setCourseName(courseDTO.getName());
			userFavoriteDTO.setCoursePrice(courseDTO.getPresentPrice());
			userFavoriteDTO.setClassHour(courseDTO.getClassHour());
			userFavoriteDTO.setStudentsNumber(courseDTO.getStudentsNumber());
			userFavoriteDTOList.add(userFavoriteDTO);
		}
		return userFavoriteDTOList;
	}

	@ApiOperation("添加购物车")
	@PostMapping("/cart")
	public void addCart(Authentication authentication, @RequestParam("courseId") Long courseId) {
		String userPhone = authentication.getName();
		User user = userService.getByPhone(userPhone);
		favoritesService.addFavorite(user.getId(), courseId, 0);
	}

	@ApiOperation("添加收藏车")
	@PostMapping("/favorite")
	public void addFavorite(Authentication authentication, @RequestParam("courseId") Long courseId) {
		String userPhone = authentication.getName();
		User user = userService.getByPhone(userPhone);
		favoritesService.addFavorite(user.getId(), courseId, 1);
	}

	@ApiOperation("删除购物车")
	@DeleteMapping("/cart")
	public void deleteCart(Authentication authentication,@RequestParam("courseId") Long courseId) {
		String userPhone = authentication.getName();
		User user = userService.getByPhone(userPhone);
		favoritesService.deleteFavorite(user.getId(), courseId, 0);
	}

	@ApiOperation("删除购物车")
	@DeleteMapping("/favorite")
	public void deleteFavorite(Authentication authentication,@RequestParam("courseId") Long courseId) {
		String userPhone = authentication.getName();
		User user = userService.getByPhone(userPhone);
		favoritesService.deleteFavorite(user.getId(), courseId, 1);
	}
}

