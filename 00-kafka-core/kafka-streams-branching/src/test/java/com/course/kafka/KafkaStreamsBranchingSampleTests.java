
package com.course.kafka;

import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class KafkaStreamsBranchingSampleTests {

	@ClassRule
	public static EmbeddedKafkaRule embeddedKafkaRule = new EmbeddedKafkaRule(1, true, "words",
			"english-counts", "french-counts", "spanish-counts");

	private static EmbeddedKafkaBroker embeddedKafka = embeddedKafkaRule.getEmbeddedKafka();

	private static Consumer<String, String> consumer;

	@Autowired
	StreamsBuilderFactoryBean streamsBuilderFactoryBean;

	@Before
	public void before() {
		streamsBuilderFactoryBean.setCloseTimeout(0);
	}

	@BeforeClass
	public static void setUp() {
		Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("group", "false", embeddedKafka);
		consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
		consumer = cf.createConsumer();
		embeddedKafka.consumeFromEmbeddedTopics(consumer, "english-counts", "french-counts", "spanish-counts");
		System.setProperty("spring.cloud.stream.kafka.streams.binder.brokers", embeddedKafka.getBrokersAsString());
	}

	@AfterClass
	public static void tearDown() {
		consumer.close();
		System.clearProperty("spring.cloud.stream.kafka.streams.binder.brokers");
	}

	@Test
	public void testKafkaStreamsWordCountProcessor() throws InterruptedException {
		Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafka);
		DefaultKafkaProducerFactory<Integer, String> pf = new DefaultKafkaProducerFactory<>(senderProps);
		try {
			KafkaTemplate<Integer, String> template = new KafkaTemplate<>(pf, true);
			template.setDefaultTopic("words");
			template.sendDefault("english");
			template.sendDefault("french");
			template.sendDefault("spanish");
			Thread.sleep(2000);
			ConsumerRecord<String, String> cr = KafkaTestUtils.getSingleRecord(consumer, "english-counts", 5000);
			assertThat(cr.value().contains("english")).isTrue();
			cr = KafkaTestUtils.getSingleRecord(consumer, "french-counts", 5000);
			assertThat(cr.value().contains("french")).isTrue();
			cr = KafkaTestUtils.getSingleRecord(consumer, "spanish-counts", 5000);
			assertThat(cr.value().contains("spanish")).isTrue();
		}
		finally {
			pf.destroy();
		}
	}

}