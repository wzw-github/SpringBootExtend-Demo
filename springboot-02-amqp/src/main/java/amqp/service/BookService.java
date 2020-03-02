package amqp.service;

import amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    /**
     * @RabbitListener(queues = "wzw.news"):指定监听wzw.news这个队列，有消息进来就输出
     * 想要使用@RabbitListener，需要开启rabbit注解，在启动类开启
     * @param book
     */
    @RabbitListener(queues = "wzw.news")
    public void receive(Book book){
        System.out.println("收到消息"+book);
    }

    @RabbitListener(queues = "wzw")
    public void receive2(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
