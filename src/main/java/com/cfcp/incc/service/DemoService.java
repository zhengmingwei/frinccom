package com.cfcp.incc.service;


import com.cfcp.incc.entity.DemoEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DemoService {
    private LinkedHashMap<Long, DemoEntity> testMap = null;

    private Date toDate(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<Long, DemoEntity> getTestMap() {
        if (testMap == null) {
            synchronized (this) {
                testMap = new LinkedHashMap<Long, DemoEntity>();
                DemoEntity p = new DemoEntity();
                p.setId(1);
                p.setName("Ming");
                p.setAge(22);
                p.setAddress("beijing");
                p.setBirthdate(toDate("1993-03-06"));
                p.setGender("M");
                p.setPhone("13921234567");
                p.setWork("5i5j");
                testMap.put(1L, p);
                p = new DemoEntity();
                p.setId(2);
                p.setName("小红");
                p.setAge(20);
                p.setAddress("上海");
                p.setBirthdate(toDate("1995-02-02"));
                p.setGender("F");
                p.setPhone("13614567890");
                p.setWork("链家");
                testMap.put(2L, p);
            }
        }
        return testMap;
    }

    public DemoEntity getDemoEntity(Long id) {
        return getTestMap().get(id);
    }

    public Long countDemoEntity(Map<String, String> conditions) {
        return (long) getTestMap().size();
    }

    public Collection<DemoEntity> queryDemoEntity(Map<String, String> conditions) {
        return getTestMap().values();
    }

    public int insertDemoEntity(DemoEntity DemoEntity) {
        Long lastId = 0L;
        for (DemoEntity p : getTestMap().values()) {
            lastId = p.getId();
        }
        DemoEntity.setId(++lastId);
        getTestMap().put(lastId, DemoEntity);
        return 1;
    }

    public int updateDemoEntity(DemoEntity DemoEntity) {
        getTestMap().put(DemoEntity.getId(), DemoEntity);
        return 1;
    }

    public int deleteDemoEntity(Long id) {
        getTestMap().remove(id);
        return 1;
    }
}
