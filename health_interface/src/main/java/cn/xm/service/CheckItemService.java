package cn.xm.service;

import cn.xm.entity.PageResult;
import cn.xm.entity.QueryPageBean;
import cn.xm.pojo.CheckItem;

import java.util.List;

/**
 * @Author: xumeng
 * @Date: 2019/12/3 11:41
 */
public interface CheckItemService {
    /**
     * 新增检查项
     */
    public void add(CheckItem checkItem);

    /**
     * 分页查询检查项
     */

    public PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     */

    public void deleteById(Integer id);


    /**
     * 批量删除
     * @param
     */
    public void deleteByIds(Integer[] ids);

    /**
     * 编辑检查项
     * @param checkItem
     */

    public void edit(CheckItem checkItem);

    /**
     * 根据id进行查询
     */

    public CheckItem findById(Integer id);

    /**
     * 查询所有检查项
     */

    public List<CheckItem> findAll();
}
