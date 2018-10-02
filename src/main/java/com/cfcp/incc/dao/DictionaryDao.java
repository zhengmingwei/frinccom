package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p/>
 *
 * @author Liulong
 * @date 2017/6/25
 * @since 0.1
 */
@Repository
public interface DictionaryDao {

	/**
	 * 根据id查找字典
	 * @param id
	 * @return
	 */
	Dictionary queryDictionaryById(String id);

    List<Dictionary> queryDictionariesByIds(String[] ids);

	/**
	 * 查询全部字典集合
	 * @return
	 */
	List<Dictionary> queryAllDictionaries();

	/**
	 * 通过字典类型查询字典集合
	 * @param type
	 * @return
	 */
	List<Dictionary> queryDictionariesByType(String type);

	List<Dictionary> findList(Map<String, String> map);
	int updateStatusById(@Param("status") Integer status, @Param("id") Integer id);
	int updateWeight(@Param("weight") Integer weight, @Param("id") String id);
	int insert(Dictionary record);
	int update(Dictionary record);
}