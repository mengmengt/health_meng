package cn.xm.controller;

import cn.xm.constant.MessageConstance;
import cn.xm.entity.PageResult;
import cn.xm.entity.QueryPageBean;
import cn.xm.entity.Result;
import cn.xm.pojo.CheckGroup;
import cn.xm.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xumeng
 * @Date: 2019/12/6 9:55
 */
@RestController
@RequestMapping("/checkGroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            checkGroupService.add(checkGroup,checkitemIds);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstance.ADD_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstance.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 分页查询检查组
     */
    @RequestMapping("/findByPage")
    public PageResult findByPage(@RequestBody QueryPageBean queryPageBean){
        PageResult page = checkGroupService.findByPage(queryPageBean);
        return  page;
    }

    /**
     * 根据id进行查询
     */
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            // 查询成功
            return new Result(true,MessageConstance.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            // 查询失败
            return new Result(false,MessageConstance.QUERY_CHECKGROUP_FAIL);
        }
    }
    /**
     * 根据id查询检查项对应的id
     */

    @RequestMapping("/findCheckItemIdsByCheckGroup")
     public Result findCheckItemIdsByCheckGroup(Integer id){
        try {
            List<Integer> checkItemIds = checkGroupService.findCheckItemIdsByCheckGroup(id);
            return new Result(true,MessageConstance.QUERY_CHECKITEM_SUCCESS,checkItemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstance.QUERY_CHECKITEM_FAIL);
        }
    }
    /**
     * 编辑检查项
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            checkGroupService.edit(checkGroup,checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstance.EDIT_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstance.EDIT_CHECKGROUP_SUCCESS);
    }

}
