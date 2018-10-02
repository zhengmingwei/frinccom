package com.cfcp.incc.controller.dictionary;

import com.cfcp.incc.controller.BaseController;
import com.cfcp.incc.dto.CommonDto;
import com.cfcp.incc.entity.Dictionary;
import com.cfcp.incc.service.DictionaryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tigerfacejs.commons.view.DataEvent;

import java.util.List;
import java.util.Map;


/**
 * Created by zhyj on 2017/7/8.
 */
@RestController
@RequestMapping("manager/dictionary")
public class DictionaryController extends BaseController {

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping(method = RequestMethod.POST)
    public Object addDictionary(@RequestBody Dictionary dictionary) {
        if (dictionaryService.saveOrUpdate(dictionary) > 0) {
            return DataEvent.wrap("dictionary", new CommonDto<Dictionary>(dictionary));
        } else {
            return DataEvent.wrap("dictionary", "保存失败");
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable String id){
        Dictionary dictionary = dictionaryService.get(id);

        return DataEvent.wrap("dictionary", new CommonDto<Dictionary>(dictionary));
    }

    @RequestMapping(value = "query")
    public Object query(@RequestParam(required = false) Map<String, String> conditions) {
        List<Dictionary> list = dictionaryService.findList(conditions);
        return DataEvent.wrap("dictionaryList", list);
    }

    @RequestMapping(value = "industryAndCategory")
    public Object industryAndCategoryMap() {
        return DataEvent.wrap("industryAndCategory", dictionaryService.industryAndCategory());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id){
        dictionaryService.delete(id);
        return DataEvent.wrap("dictionaryDeleted", new CommonDto<>(CommonDto.CommonResult.SUCCESS));
    }

}
