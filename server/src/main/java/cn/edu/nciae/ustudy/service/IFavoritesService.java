package cn.edu.nciae.ustudy.service;

import cn.edu.nciae.ustudy.po.Favorites;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author YeLi
 * @date 2020-09-06
 */
public interface IFavoritesService extends IService<Favorites> {

	void addFavorite(Long userId, Long courseId, Integer type);

	void deleteFavorite(Long userId, Long courseId, Integer type);
}
