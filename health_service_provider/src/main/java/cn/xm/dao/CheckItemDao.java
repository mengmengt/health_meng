package cn.xm.dao;

import cn.xm.entity.PageResult;
import cn.xm.entity.QueryPageBean;
import cn.xm.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;


/**
 * @Author: xumeng
 * @Date: 2019/12/3 14:31
 */
public interface CheckItemDao {
     void add(CheckItem checkItem);

     PageResult findPage(QueryPageBean queryPageBean);

     Page<CheckItem> selectByCondition(String queryString);

     /**
      * 查询检查项是否关联到检查组
      */

     long findCheckItemByCheckGroup(Integer id);

     /**
      * 删除检查项
      */

     void deleteById(Integer id);

     /**
      * 编辑检查项
      */
     void edit(CheckItem checkItem);


     /**
      * 根据di进行查询
      * @param id
      * @return
      */

     CheckItem findById(Integer id);

    /**
     * 查询所有检查项
     */

    List<CheckItem> findAll();
}
