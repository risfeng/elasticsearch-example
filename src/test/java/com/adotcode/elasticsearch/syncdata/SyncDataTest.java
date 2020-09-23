package com.adotcode.elasticsearch.syncdata;

import com.adotcode.elasticsearch.service.es.ElasticSearchService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 同步数据
 *
 * @author risfeng
 * @date 2020/9/22
 */
@SpringBootTest
class SyncDataTest {

    @Resource
    private ElasticSearchService elasticSearchService;

    @Test
    void syncDataToEsTest() {
        Boolean aBoolean = elasticSearchService.syncQuestionsDataToEs();
        System.out.println("同步结果：" + aBoolean);
    }
}
