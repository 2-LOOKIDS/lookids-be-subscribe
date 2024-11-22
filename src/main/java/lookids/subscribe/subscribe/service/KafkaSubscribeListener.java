package lookids.subscribe.subscribe.service;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.subscribe.subscribe.domain.Subscribe;
import lookids.subscribe.subscribe.dto.in.KafkaAlarmRequestDto;
import lookids.subscribe.subscribe.dto.in.KafkaFeedRequestDto;
import lookids.subscribe.subscribe.repository.SubscribeRepository;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
public class KafkaSubscribeListener {

	private final SubscribeRepository subscribeRepository;
	private final KafkaTemplate<String, KafkaAlarmRequestDto> kafkaTemplate;

	@KafkaListener(topics = "feed-create", groupId = "feed-join-subscribe", containerFactory = "feedEventListenerContainerFactory")
	public void consumeFeedEvent(KafkaFeedRequestDto kafkaFeedRequestDto) {

		log.info("consumeFeedEvent: {}", kafkaFeedRequestDto);

		List<Subscribe> authorUuidList = subscribeRepository.findByAuthorUuid(kafkaFeedRequestDto.getSenderUuid());

		List<String> receiverUuidList = authorUuidList
			.stream()
			.map(Subscribe::getSubscriberUuid)
			.toList();

		KafkaAlarmRequestDto kafkaAlarmRequestDto = KafkaAlarmRequestDto.toDto(kafkaFeedRequestDto, receiverUuidList);

		sendMessage("feed-create-join-subscribe", kafkaAlarmRequestDto);
	}

	public void sendMessage(String topic, KafkaAlarmRequestDto kafkaAlarmRequestDto) {
		kafkaTemplate.send(topic, kafkaAlarmRequestDto);
	}
}
