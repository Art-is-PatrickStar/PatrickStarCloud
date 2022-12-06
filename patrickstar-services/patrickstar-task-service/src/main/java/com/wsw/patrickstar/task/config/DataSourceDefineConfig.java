package com.wsw.patrickstar.task.config;

import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.core.yaml.config.sharding.YamlShardingRuleConfiguration;
import org.apache.shardingsphere.core.yaml.swapper.impl.ShardingRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: 数据源定义类
 * @Author: wangsongwen
 * @DateTime: 2022/8/1 16:04
 */
@Configuration
public class DataSourceDefineConfig {
    /**
     * 默认数据源，采用ShardingJdbc数据源
     */
    @Bean(name = "taskDataSource")
    public DataSource getTaskDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfigurationYamlSwapper().swap(getTaskShardingRules());
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", getTaskRawDataSource());
        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, getTaskShardingProperties());
    }

    /**
     * 原始数据源
     * 定义它是为了给sharding数据源提供支持，请不要在代码中直接使用该数据源
     */
    @Bean(name = "taskRawDataSource")
    @ConfigurationProperties(prefix = "datasource.task")
    public DataSource getTaskRawDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * sharding-jdbc properties
     */
    @Bean(name = "taskShardingProperties")
    @ConfigurationProperties(prefix = "sharding-props.task")
    public Properties getTaskShardingProperties() {
        return new Properties();
    }

    /**
     * sharding-jdbc rule
     */
    @Bean(name = "taskShardingRules")
    @ConfigurationProperties(prefix = "sharding-rules.task")
    public YamlShardingRuleConfiguration getTaskShardingRules() {
        return new YamlShardingRuleConfiguration();
    }

    /**
     * h2数据源
     */
    @Bean(name = "h2DataSource")
    @ConfigurationProperties(prefix = "datasource.h2")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 这里注入的是dynamic datasource
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
