package com.cfcp.incc.service;

import com.cfcp.incc.dao.DictionaryDao;
import com.cfcp.incc.entity.Dictionary;
import com.cfcp.incc.utils.DateUtils;
import com.cfcp.incc.utils.generator.GeneratorComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p/>
 *
 * @author Liulong
 * @date 2016/11/25
 * @since 0.1
 */
@Service
public class DictionaryService extends BaseService {

	@Autowired
	private DictionaryDao dictionaryDao;

	public static final String TYPE_TAG = "tag";
	public static final String TYPE_TRAVELS = "travels";
	/**
	 * 根据id查询字典
	 * @param id
	 * @return
	 */
	public Dictionary findDictionaryById(String id){
		return dictionaryDao.queryDictionaryById(id);
	}
	public List<Dictionary> findDictionariesByIds(String[] ids){
		List<Dictionary> dictionaryList=dictionaryDao.queryDictionariesByIds(ids);
		return dictionaryList;
	}

	/**
	 * 查询所有字典集合
	 * @return
	 */
	public List<Dictionary> findAllDictionaries(){
		return dictionaryDao.queryAllDictionaries();
	}

	/**
	 * 根据字典类型查询字典集合
	 * @param type
	 * @return
	 */
	public List<Dictionary> findDictionariesByType(String type){
		return dictionaryDao.queryDictionariesByType(type);
	}

	/**
	 * 处理字典id字符串，返回字典集合
	 * @param tagLabel
	 * @return
	 */
	public List<Dictionary> sortingDictionary(String tagLabel){
		List<Dictionary> tagList=new ArrayList<>();
		if(StringUtils.hasText(tagLabel)){
			String[] dicIdArr=tagLabel.split(",");
			for(String dicId:dicIdArr){
				Dictionary dictionary=findDictionaryById(dicId);
				tagList.add(dictionary);
			}
		}
		return tagList;
	}

	/**
	 * 获取list
	 * @param map
	 * @return
	 */
	public List<Dictionary> findList(Map<String,String> map){
		List<Dictionary> list =dictionaryDao.findList(map);
		return list;
	}
	public int updateWeight(Integer weight, String id) {
		return dictionaryDao.updateWeight(weight,id);
	}
//	public int updateStatus(Integer status, String id) {
//		return dictionaryDao.updateStatusById(status,id);
//	}

	public Map<String, Dictionary> dictionariesMap(){
		List<Dictionary> list = dictionaryDao.queryAllDictionaries();
		Map map = new HashMap();
		list.forEach(dictionary -> map.put(dictionary.getId().toString(), dictionary));
		return map;
	}

	public Map<String, Dictionary> industryAndCategory(){
		List<Dictionary> list = dictionaryDao.queryAllDictionaries();
		Map<String, Dictionary> map = new LinkedHashMap();
		list.forEach(dictionary ->{if("INDUSTRY".equals(dictionary.getType()))map.put(dictionary.getId().toString(), dictionary);});
		list.forEach(dictionary ->{if("CATEGORY".equals(dictionary.getType())){
			Dictionary parent = map.get(dictionary.getParentId().toString());
			parent.addChild(dictionary);
		} });
		return map;
	}
//
//	public Map<String, Dictionary> reportTypeMap(){
//		List<Dictionary> list = dictionaryDao.queryAllDictionaries();
//		Map map = new HashMap();
//		list.forEach(dictionary -> {if("REPORT_TYPE".equals(dictionary.getType()))map.put(dictionary.getId().toString(), dictionary)});
//		return map;
//	}

	public Dictionary get(String id){
		return dictionaryDao.queryDictionaryById(id);
	}

	/**
	 * 保存
	 * @param dictionary
	 * @return
	 */
	public int saveOrUpdate(Dictionary dictionary) {
		if (dictionary == null){
			return 0;
		} else {
			if (dictionary.getId() != null){
				return dictionaryDao.update(dictionary);
			} else {
				dictionary.setStatus(1);
				dictionary.setCreateTime(new Date());
				return dictionaryDao.insert(dictionary);
			}
		}
	}

	public int delete(Integer id){
		return dictionaryDao.updateStatusById(0, id);
	}
}
