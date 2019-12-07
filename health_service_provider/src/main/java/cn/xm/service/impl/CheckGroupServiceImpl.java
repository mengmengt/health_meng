package cn.xm.service.impl;

import cn.xm.dao.CheckGroupDao;
import cn.xm.entity.PageResult;
import cn.xm.entity.QueryPageBean;
import cn.xm.pojo.CheckGroup;
import cn.xm.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xumeng
 * @Date: 2019/12/6 10:01
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService{

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 新增检查项
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        System.out.println("插入前"+ JSON.toJSONString(checkGroup));
           // 操作表t_checkGroup，添加检查项
          checkGroupDao.add(checkGroup);

        System.out.println("插入后"+JSON.toJSONString(checkGroup));
          //操作表t_checkGroup_checkItem，设置检查项和检查组的多对多的关系
          // 1 获取新增检查项的id
          Integer checkGroupId = checkGroup.getId();
        // 遍历一个检查组对应的检查项
        if(checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<>();
                map.put("checkGroupId", checkGroupId);
                map.put("checkItemId", checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

    /**
     * 分页查询检查组
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> byCondition = checkGroupDao.findByCondition(queryString);
        long total = byCondition.getTotal();
        List<CheckGroup> rows = byCondition.getResult();
        return new PageResult(total,rows);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }


    /**
     * 查询关联项id
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroup(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroup(id);
    }

    /**
     * 编辑检查组信息，同时关联
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 编辑基本信息
        checkGroupDao.edit(checkGroup);
        // 清除检查组对应检查项的关联关系
        checkGroupDao.deleteAssociation(checkGroup.getId());
        // 获取当前检查项对应的id
       Integer checkgroupid =  checkGroup.getId();
       // 遍历检查项的集合，建立检查项与检查组之间的关系
       if(checkitemIds.length>0 && checkitemIds != null){
           for (Integer checkitemId : checkitemIds) {
               Map<String, Integer> map = new HashMap<>();
               map.put("checkGroupId",checkgroupid);
               map.put("checkItemId",checkitemId);
               checkGroupDao.setCheckGroupAndCheckItem(map);
           }
       }
    }
}
