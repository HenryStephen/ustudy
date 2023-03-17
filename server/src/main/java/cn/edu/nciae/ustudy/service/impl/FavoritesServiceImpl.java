package cn.edu.nciae.ustudy.service.impl;

import cn.edu.nciae.ustudy.mapper.FavoritesMapper;
import cn.edu.nciae.ustudy.po.Favorites;
import cn.edu.nciae.ustudy.service.IFavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author YeLi
 * @author zhang
 */
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements IFavoritesService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addFavorite(Long userId, Long courseId, Integer type) {
		Favorites favorites = new Favorites();
		List<Favorites> favoritesList = lambdaQuery()
				.eq(Favorites::getType,type)
				.eq(Favorites::getUserId,userId)
				.eq(Favorites::getCourseId,courseId).list();
		if(favoritesList.isEmpty()){
			favorites.setAddedTime(LocalDateTime.now());
			favorites.setCourseId(courseId);
			favorites.setType(type);
			favorites.setUserId(userId);
			save(favorites);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteFavorite(Long userId, Long courseId,Integer type) {
		List<Favorites> favoritesList = lambdaQuery()
				.eq(Favorites::getType,type)
				.eq(Favorites::getUserId,userId)
				.eq(Favorites::getCourseId,courseId).list();
		if(!favoritesList.isEmpty()){
			for (Favorites favorites : favoritesList){
				Long id = favorites.getId();
				removeById(id);
			}
		}
	}
}
