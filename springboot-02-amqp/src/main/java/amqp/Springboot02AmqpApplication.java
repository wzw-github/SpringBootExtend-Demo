package amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 	1、RabbitAutoConfiguration
 * 	2、有自动配置了连接工厂ConnectionFactory
 * 	3、RabbitProperties封装了Rabbitmq的配置
 *	4、RabbitTemplate：给Rabbitmq发送消息和接收消息
 *	5、AmqpAdmin：Rabbitmq系统管理功能组件
 *			AmqpAdmin：创建和删除Queue、Exchange和Binding
 *	6、@EnableRabbit+@RabbitListener监听消息队列的内容
 */
@EnableRabbit	//开启rabbit注解
@SpringBootApplication
public class Springboot02AmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot02AmqpApplication.class, args);
	}

}
