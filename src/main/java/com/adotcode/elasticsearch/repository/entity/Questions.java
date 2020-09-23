package com.adotcode.elasticsearch.repository.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 题目表
 *
 * @author risfeng
 * @date 2020/9/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "questions")
public class Questions implements Serializable {

    private static final long serialVersionUID = -4446684934509106514L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 试题-题干
     */
    @TableField(value = "title")
    private String title;

    /**
     * 选项A
     */
    @TableField(value = "option_a")
    private String optionA;

    /**
     * 选项B
     */
    @TableField(value = "option_b")
    private String optionB;

    /**
     * 选项C
     */
    @TableField(value = "option_c")
    private String optionC;

    /**
     * 选项D
     */
    @TableField(value = "option_d")
    private String optionD;

    /**
     * 选项E
     */
    @TableField(value = "option_e")
    private String optionE;

    /**
     * 标准格式答案，多选题答案为A|B格式，单选题A
     */
    @TableField(value = "answer1")
    private String answer1;

    /**
     * 非标准格式答案或含部分过程说明的答案
     */
    @TableField(value = "answer2")
    private String answer2;

    /**
     * 试题解析
     */
    @TableField(value = "parse")
    private String parse;

    /**
     * 试题题型
     */
    @TableField(value = "qtype")
    private String qtype;

    /**
     * 难度
     */
    @TableField(value = "diff")
    private Integer diff;

    /**
     * 试题题干的md5值
     */
    @TableField(value = "md5")
    private String md5;

    /**
     * 学科Id
     */
    @TableField(value = "subject_id")
    private Byte subjectId;

    /**
     * 年级ID
     */
    @TableField(value = "grade_id")
    private Integer gradeId;

    /**
     * 知识点名称
     */
    @TableField(value = "knowledge")
    private String knowledge;

    /**
     * 试题区域
     */
    @TableField(value = "area")
    private String area;

    /**
     * 试题年份
     */
    @TableField(value = "`year`")
    private Integer year;

    /**
     * 试题类型：1，月考；2，模拟考；3，中考；4，高考；5，学业考试；6，其他
     */
    @TableField(value = "paper_type")
    private String paperType;

    /**
     * 试题来源(试卷)
     */
    @TableField(value = "`source`")
    private String source;

    /**
     * 试题来源（网站）
     */
    @TableField(value = "from_site")
    private String fromSite;

    /**
     * 是否存在图片水印
     */
    @TableField(value = "is_sub")
    private Boolean isSub;

    /**
     * 是否常规题，如果选择题无法正常提取标准答案或者选项，有小题的答题无法正常提取小题，则isNormal为0，否则为1
     */
    @TableField(value = "is_normal")
    private Boolean isNormal;

    /**
     * 是否匹配章节知识点，1匹配，0不匹配
     */
    @TableField(value = "is_know")
    private Boolean isKnow;

    /**
     * 试题的tiid，结合fromsite进行同网站试题排重，用于增量更新
     */
    @TableField(value = "ti_id")
    private String tiId;

    /**
     * 试题在题库中的相似度，相似度越高，质量越低
     */
    @TableField(value = "similarity")
    private Integer similarity;

    @TableField(value = "is_unique")
    private Boolean isUnique;

    @TableField(value = "md52")
    private String md52;
}