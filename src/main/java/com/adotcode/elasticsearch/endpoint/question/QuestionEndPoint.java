package com.adotcode.elasticsearch.endpoint.question;

import com.adotcode.elasticsearch.common.result.HttpResult;
import com.adotcode.elasticsearch.repository.entity.Questions;
import com.adotcode.elasticsearch.service.question.QuestionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 题目数据库操作Api
 *
 * @author risfeng
 * @date 2020/9/20
 */
@RestController
@ResponseBody
@RequestMapping("/api/v1/questions")
public class QuestionEndPoint {

    @Resource
    private QuestionService questionService;


    /**
     * 分页查询
     */
    @ResponseBody
    @GetMapping("/page")
    public HttpResult<IPage<Questions>> get(
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize) {
        IPage<Questions> page = new Page<>(pageIndex, pageSize);
        IPage<Questions> result = questionService.page(page);
        return HttpResult.ok(result);
    }

}
