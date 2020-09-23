package com.adotcode.elasticsearch.service.es.dto;

import com.adotcode.elasticsearch.common.utils.GsonUtils;
import com.adotcode.elasticsearch.repository.entity.Questions;
import lombok.Builder;
import lombok.Data;

/**
 * ES DTO
 *
 * @author risfeng
 * @date 2020/9/20
 */
@Data
@Builder
public class QuestionElasticSearchDTO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 试题-题干
     */
    private String title;


    public static QuestionElasticSearchDTO valueOf(Questions questions) {
        return QuestionElasticSearchDTO.builder()
                .id(questions.getId())
                .title(questions.getTitle())
                .build();
    }

    /**
     * 序列化为Json
     *
     * @return json字符串
     */
    @Override
    public String toString() {
        return GsonUtils.getInstance().toJson(this);
    }
}
