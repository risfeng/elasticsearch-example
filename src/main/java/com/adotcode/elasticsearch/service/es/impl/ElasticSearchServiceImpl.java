package com.adotcode.elasticsearch.service.es.impl;

import com.adotcode.elasticsearch.repository.entity.Questions;
import com.adotcode.elasticsearch.service.es.ElasticSearchService;
import com.adotcode.elasticsearch.service.es.handler.SyncData2EsHandler;
import com.adotcode.elasticsearch.service.question.QuestionService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

/**
 * ES服务实现
 *
 * @author risfeng
 * @date 2020/9/20
 */
@Slf4j
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    /**
     * 同步处理器
     */
    @Resource
    private SyncData2EsHandler<Questions> syncData2EsHandler;

    @Resource
    private QuestionService questionService;

    /**
     * 线程池
     */
    private final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

    public ElasticSearchServiceImpl() {
        // 设置核心线程数
        executor.setCorePoolSize(8);
        // 设置最大线程数
        executor.setMaxPoolSize(10);
        // 设置队列容量
        executor.setQueueCapacity(100);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix("syncData-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 初始化
        executor.initialize();
    }


    /**
     * 同步数据到Es
     *
     * @return 是否成功
     */
    @Override
    public Boolean syncQuestionsDataToEs() {
        AtomicLong pageIndex = new AtomicLong(1);
        long pageSize = 1000;
        String index = "questions";
        String primaryKeyName = "id";
        while (true) {
            Page<Questions> page = new Page<>(pageIndex.getAndIncrement(), pageSize);
            IPage<Questions> result = questionService.page(page);
            if (page.getCurrent() > result.getPages() || ObjectUtils.isEmpty(result.getRecords())) {
                log.info("同步结束，无数据。页码：{}", page.getCurrent());
                break;
            }
            // 线程池执行同步
            executor.execute(() -> syncData2EsHandler.syncQuestionDataToEs(result.getRecords(), primaryKeyName, index));
            log.info("正在同步，当前页码：{}", page.getCurrent());
        }
        return true;
    }
}
