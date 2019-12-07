package cn.xm.controller;

import cn.xm.constant.MessageConstance;
import cn.xm.entity.PageResult;
import cn.xm.entity.QueryPageBean;
import cn.xm.entity.Result;
import cn.xm.pojo.CheckItem;
import cn.xm.service.CheckItemService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xumeng
 * @Date: 2019/12/3 11:35
 */
@RestController
@RequestMapping("/checkItem")
public class CheckItemController {
    @Reference   // 查找服务
    private CheckItemService checkItemService;
    /**
     * 增加检查项
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        System.out.println("========="+checkItem);
        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //调用服务失败
            return new Result(false, MessageConstance.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstance.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询检查项
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult  pageResult = checkItemService.findPage(queryPageBean);
        System.out.println(pageResult.getRows().size());
        return pageResult;
    }



    /**
     * 删除检查项
     */
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkItemService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            //调用服务失败
            return new Result(false, MessageConstance.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstance.DELETE_CHECKITEM_SUCCESS);
    }
    //批量删除
    @RequestMapping("/deleteByIds")
    public Result deleteByIds(Integer[] ids){
        try {
            checkItemService.deleteByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            //调用服务失败
            return new Result(false, MessageConstance.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstance.DELETE_CHECKITEM_SUCCESS);
    }

    /**
     * 编辑检查项
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            //调用服务失败
            return new Result(false, MessageConstance.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstance.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 根据id进行查询
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckItem   data = checkItemService.findById(id);
            System.out.println(data);
            return new Result(true, MessageConstance.QUERY_CHECKITEM_SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstance.QUERY_CHECKITEM_FAIL);
        }

    }
    /**
     * 查询所有检查项
     */

    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<CheckItem> data = checkItemService.findAll();
            return new Result(true, MessageConstance.QUERY_CHECKITEM_SUCCESS,data);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstance.QUERY_CHECKITEM_SUCCESS);

        }
    }
}
