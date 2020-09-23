package com.adotcode.elasticsearch.endpoint.es;

import com.adotcode.elasticsearch.common.result.HttpResult;
import com.adotcode.elasticsearch.service.es.ElasticSearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ES操作Api
 *
 * @author risfeng
 * @date 2020/9/20
 */
@RestController
@ResponseBody
@RequestMapping("/api/v1/es")
public class ElasticSearchEndPoint {

    @Resource
    private ElasticSearchService elasticSearchService;


    /**
     * 创建索引
     */
//    @ResponseBody
//    @GetMapping("/create-index")
//    public HttpResult<Boolean> createIndex(@RequestParam("indexName") String indexName) {
//        return HttpResult.ok(elasticSearchService.createIndex(indexName));
//    }

    /**
     * 同步数据
     */
    @ResponseBody
    @GetMapping("/sync-data")
    public HttpResult<Boolean> syncData() {
        return HttpResult.ok(elasticSearchService.syncQuestionsDataToEs());
    }

}
