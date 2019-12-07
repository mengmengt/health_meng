package cn.xm.dao;


import cn.xm.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author: xumeng
 * @Date: 2019/12/6 10:01
 */
public interface CheckGroupDao {
    /**
     * 新增检查组
     * @param checkGroup
     */
    public void add(CheckGroup checkGroup);

    /**
     * 设置检查项和检查组的关联关系
     */

    public  void setCheckGroupAndCheckItem(Map map);

    /**
     * 根据条件查询
     */

    public Page<CheckGroup> findByCondition(String queryString);

    /**
     * 根据id查询
     */
    public CheckGroup findById(Integer id);

    /**
     * 查询关联项
     */

    public List<Integer> findCheckItemIdsByCheckGroup( Integer id);

    /**
     * 编辑检查组
     * @param checkGroup
     */
    public void edit(CheckGroup checkGroup);

    /**
     * 清除对应的关联关系
     */

    public void deleteAssociation(Integer id);
}
