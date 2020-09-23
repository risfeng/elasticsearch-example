package com.adotcode.elasticsearch.service.es;

/**
 * ES服务
 *
 * @author risfeng
 * @date 2020/9/20
 */
public interface ElasticSearchService {


    /**
     * 同步数据到Es
     *
     * @return 全部数据已经交给同步线程池处理
     */
    Boolean syncQuestionsDataToEs();
}
