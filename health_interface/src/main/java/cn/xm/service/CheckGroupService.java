package cn.xm.service;

/**
 * @Author: xumeng
 * @Date: 2019/12/6 9:55
 */

import cn.xm.entity.PageResult;
import cn.xm.entity.QueryPageBean;
import cn.xm.pojo.CheckGroup;

import java.util.List;

/**
 * 检查项
 */
public interface CheckGroupService {
    /**
     * 新增检查项
     */
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 分页查询检查组
     */
    public PageResult findByPage(QueryPageBean queryPageBean);

    /**
     * 根据id进行查询
     */

    public CheckGroup findById(Integer Id);

    /**
     * 查询关联检查项id
     */

    public List<Integer> findCheckItemIdsByCheckGroup(Integer id);

    /**
     * 编辑检查组
     * @param checkGroup
     * @param checkitemIds
     */

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);
}
