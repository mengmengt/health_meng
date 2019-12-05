package cn.xm.service.impl;

import cn.xm.dao.CheckItemDao;
import cn.xm.entity.PageResult;
import cn.xm.entity.QueryPageBean;
import cn.xm.pojo.CheckItem;
import cn.xm.service.CheckItemService;
import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: xumeng
 * @Date: 2019/12/3 14:30
 */

/**
 * 检查服务项
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    // 注入Dao对象
    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        //基于mybatis框架的分页插件
       PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();

        return new PageResult(total,rows) ;
    }


    /**
     * 删除检查项
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        //判断检查项是否关联到检查组
        long count = checkItemDao.findCheckItemByCheckGroup(id);
        if(count > 0){
            new RuntimeException("当前检查项被引用，无法删除");
        }

        checkItemDao.deleteById(id);
    }


    /**
     * 编辑检查项
     * @param checkItem
     */
    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }


    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }


    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteByIds(Integer[] ids) {
        for (Integer id : ids) {
            checkItemDao.deleteById(id);
        }
    }

}
