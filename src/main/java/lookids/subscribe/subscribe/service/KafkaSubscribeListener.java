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
	private final String TYPE = "feed";

	// 새로운 피드가 생성되면 feed-create 토픽으로 메시지가 발행
	// 리스너가 해당 메시지를 수신
	// 피드 작성자의 구독자 목록을 조회
	// 구독자들에게 알람을 보내기 위한 새로운 메시지를 생성
	// 알람 메시지를 feed-create-join-subscribe 토픽으로 발행

	@KafkaListener(topics = "feed-create"
		, groupId = "feed-join-subscribe"
		, containerFactory = "feedEventListenerContainerFactory")
	public void consumeFeedEvent(KafkaFeedRequestDto kafkaFeedRequestDto) {

		log.info("consumeFeedEvent: {}", kafkaFeedRequestDto);

		List<Subscribe> authorUuidList = subscribeRepository.findByAuthorUuid(kafkaFeedRequestDto.getUuid());

		List<String> receiverUuidList = authorUuidList
			.stream()
			.map(Subscribe::getSubscriberUuid)
			.toList();

		String splitedContent = kafkaFeedRequestDto.getContent().length() > 20 ? kafkaFeedRequestDto.getContent().substring(0, 20) + "..." : kafkaFeedRequestDto.getContent();

		KafkaAlarmRequestDto kafkaAlarmRequestDto = KafkaAlarmRequestDto.toDto(kafkaFeedRequestDto, receiverUuidList, splitedContent, TYPE);

		sendMessage("feed-create-join-subscribe", kafkaAlarmRequestDto);
	}

	public void sendMessage(String topic, KafkaAlarmRequestDto kafkaAlarmRequestDto) {
		kafkaTemplate.send(topic, kafkaAlarmRequestDto);
	}
}
