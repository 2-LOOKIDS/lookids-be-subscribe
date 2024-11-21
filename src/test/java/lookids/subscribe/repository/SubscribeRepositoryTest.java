package lookids.subscribe.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lookids.subscribe.subscribe.dto.out.SubscribeResponseDto;
import lookids.subscribe.subscribe.repository.SubscribeRepository;
import lookids.subscribe.subscribe.service.SubscribeServiceImpl;

@SpringBootTest

public class SubscribeRepositoryTest {
	@Autowired
	private SubscribeRepository subscribeRepository;

	// @Test
	// public void testBulkSubscribersRegistration() {
	// 	Random random = new Random();
	// 	// 주어진 authorUuid
	// 	String authorUuid = "normal-12345678";
	//
	// 	// 500명의 subscriberUuid 생성
	// 	List<String> subscriberUuids = IntStream.range(1, 9001)
	// 		.mapToObj(i -> "subscriber-" + String.format("%05d", random.nextInt(100000)))
	// 		.collect(Collectors.toList());
	//
	// 	// 각 subscriberUuid에 대해 Subscribe 엔티티 생성 및 저장
	// 	for (String subscriberUuid : subscriberUuids) {
	// 		Subscribe subscribe = Subscribe.builder()
	// 			.authorUuid(authorUuid)
	// 			.subscriberUuid(subscriberUuid)
	// 			.build();
	// 		subscribeRepository.save(subscribe);
	// 	}
	//
	// }


	@Test
	public void testQueryTimeForNormalAuthor() {
		// 주어진 authorUuid
		String authorUuid = "normal-12345678";

		// 서비스 인스턴스 주입 (예: @Autowired 또는 생성자 주입)
		SubscribeServiceImpl subscribeService = new SubscribeServiceImpl(subscribeRepository);

		// 조회 시작 시간 측정
		long startTime = System.nanoTime();

		// authorUuid로 구독자 조회
		SubscribeResponseDto response = subscribeService.readSubscribers(authorUuid);

		// 조회 종료 시간 측정
		long endTime = System.nanoTime();


		// 조회 시간 계산 (밀리초 단위로 변환)
		long durationInMillis = (endTime - startTime) / 1_000_000;

		// 결과 출력
		System.out.println("조회 시간: " + durationInMillis + " ms");

		// 결과 검증 (예: 구독자가 존재해야 함)
		assertThat(response.getSubscriberUuids()).isNotEmpty();
	}

}
