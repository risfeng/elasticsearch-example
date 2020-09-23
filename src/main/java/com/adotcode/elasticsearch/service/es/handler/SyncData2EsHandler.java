package com.adotcode.elasticsearch.service.es.handler;

import com.adotcode.elasticsearch.common.utils.GsonUtils;
import com.adotcode.elasticsearch.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 同步数据到Es处理器
 *
 * @author risfeng
 * @date 2020/9/23
 */
@Slf4j
@Component
public class SyncData2EsHandler<T> {

    /**
     * es client
     */
    @Resource
    private RestHighLevelClient restHighLevelClient;


    /**
     * 同步题目到ES
     *
     * @param questionsList  题目列表
     * @param primaryKeyName 主键字段名
     * @param indexName      索引名
     */
    public void syncQuestionDataToEs(List<T> questionsList, String primaryKeyName, String indexName) {
        Assert.isTrue(ObjectUtils.isNotEmpty(questionsList), "数据列表不可为空.");
        Assert.isTrue(StringUtils.isNotBlank(primaryKeyName), "主键字段名不可为空.");
        Assert.isTrue(StringUtils.isNotBlank(indexName), "索引名不可为空.");

        BulkRequest request = new BulkRequest();
        for (T record : questionsList) {
            IndexRequest indexRequest = new IndexRequest(indexName);
            Object primaryValue = ReflectionUtils.getFieldValue(record, primaryKeyName);
            indexRequest.id(String.valueOf(primaryValue));
            String questionJson = GsonUtils.getInstance().toJson(record);
            indexRequest.source(questionJson, XContentType.JSON);
            request.add(indexRequest);
        }
        try {
            BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
            log.info("批次【{}】同步ES结果【{}】", UUID.randomUUID(), bulk.hasFailures() ? "失败" : "成功");
        } catch (IOException e) {
            log.error("同步ES失败", e);
        }

    }

}
