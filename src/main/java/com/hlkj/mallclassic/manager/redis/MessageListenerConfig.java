package com.hlkj.mallclassic.manager.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @program: mall-classic
 * @description: MessageListenerConfig配置：将监听器加入加监听容器
 * @author: 李向平
 * @create: 2021-04-02 21:44
 */
@Configuration
public class MessageListenerConfig {

    @Value("${spring.redis.listen-pattern}")
    private String pattern;

    @Bean
    public RedisMessageListenerContainer listenerContainer(RedisConnectionFactory redisConnection){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnection);
        //确定监听主题
        PatternTopic topic = new PatternTopic(this.pattern);
        //将监听器加入监听容器
        container.addMessageListener(topicMessageListener(),topic);
        System.out.println("注册了redis消费者");
        return container;
    }

    @Bean
    public TopicMessageListener topicMessageListener() {
        return TopicMessageListener.getSingleton1();
    }

}
