package amqp;

import amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Springboot02AmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	/**
	 * 创建组件都是decleare开头
	 * 删除都是delete开头
	 */
	@Test
	void createExchange(){

		// 创建一个exchange
		// amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));

		//创建一个Queue，durable持久化的
//		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

		//将交换器和队列绑定
		amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,
				"amqpadmin.exchange","amqp.haha",null));

		System.out.println("创建完成");
	}


	/**
	 * 1、点对点
	 */
	@Test
	void contextLoads() {
		//message需要自己构造，定义消息体内容和消息头
		//rabbitTemplate.send(exchange,routekey,message);

		Map<String,Object> map=new HashMap<>();
		map.put("msg1","第一条消息");
		map.put("msg2","第二条消息");

		//convertAndSend自动构造消息，不需要定义消息体内容和消息头
		//对象会被默认序列化后发送
		rabbitTemplate.convertAndSend("exchange.direct","wzw.news",map);

		rabbitTemplate.convertAndSend("exchange.direct","wzw.news",new Book("西游记","张三"));
	}

	@Test
	void receive(){
		Object object=rabbitTemplate.receiveAndConvert("wzw.news");
		//查看数据类型
		System.out.println("数据类型："+object.getClass());
		System.out.println(object);
	}

	/**
	 * 广播fanout
	 */
	@Test
	void testFanout(){
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("红楼梦","李四"));
	}

	/**
	 * 测试topic
	 */
	@Test
	void testTopic(){
		rabbitTemplate.convertAndSend("exchange.topic","*.news",new Book("西游记","张三"));
	}

}
