package com.adotcode.elasticsearch.service.question.impl;

import com.adotcode.elasticsearch.repository.entity.Questions;
import com.adotcode.elasticsearch.repository.mapper.QuestionsMapper;
import com.adotcode.elasticsearch.service.question.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author risfeng
 * @date 2020/9/20
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionsMapper, Questions> implements QuestionService {
}
