package com.c4.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.c4.bean.EduTeacher;
import com.c4.bean.vo.TeacherQuery;
import com.c4.service.EduTeacherService;
import com.c4.utlis.CommonResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class EduTeacherController {
    @Resource
    private EduTeacherService es;

    @GetMapping("eduteacher/all")
    public CommonResult allTeacher(){
        return CommonResult.success().data("item",es.list());
    }
    @DeleteMapping("delete/{id}")
    public CommonResult deleteById(@PathVariable("id") String  id){
        boolean b = es.removeById(id);
        return b?CommonResult.success():CommonResult.error();
    }
    /*
    分页查询teacher
    current:当前页
    limit: 每页条数
     */
    @GetMapping("getPage/{current}/{limit}")
    public CommonResult getPage(@PathVariable Integer current,@PathVariable Integer limit){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //调用page()把分页的数据存入pageTeacher中
        es.page(pageTeacher,null);
        long total = pageTeacher.getTotal();//总条数
        long pages = pageTeacher.getPages();//总页数
        List<EduTeacher> records = pageTeacher.getRecords();//每页数据集合
        //方式一
//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("pages",pages);
//        map.put("records",records);
//        return CommonResult.success().setData(map);
        //方式二
        return CommonResult.success().data("total",total).data("pages",pages).data("records",records);
    }
    /*
        分页带条件查询teacher
        current:当前页
        limit: 每页条数
     */
    @GetMapping("getPageCondition/{current}/{limit}")
    public CommonResult getPageCondition(@PathVariable Integer current, @PathVariable Integer limit, TeacherQuery vo){
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = vo.getName();
        Integer level = vo.getLevel();
        String begin = vo.getBegin();
        String end = vo.getEnd();
        if (!ObjectUtil.isNull(name)) {
            //根据名字模糊查询
            wrapper.like("name",name);
        }
        if(!ObjectUtil.isNull(level)){
            wrapper.eq("level",level);
        }
        if(!ObjectUtil.isNull(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!ObjectUtil.isNull(end)){
            wrapper.le("gmt_create",end);
        }
        //调用page()把分页的数据存入pageTeacher中
        es.page(pageTeacher,wrapper);
        long total = pageTeacher.getTotal();//总条数
        long pages = pageTeacher.getPages();//总页数
        List<EduTeacher> records = pageTeacher.getRecords();//每页数据集合

        return CommonResult.success().data("total",total).data("pages",pages).data("records",records);
    }

}
