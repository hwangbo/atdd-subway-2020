package wooteco.subway.maps.map.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.common.collect.Lists;
import wooteco.subway.common.TestObjectUtils;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.maps.line.domain.LineStation;
import wooteco.subway.maps.station.domain.Station;
import wooteco.subway.members.member.domain.LoginMember;

class SubwayPathTest {
	private static LoginMember member1 = new LoginMember(1L, "abc@email.com", "abcd1234", 35);
	private static LoginMember member2 = new LoginMember(2L, "abc@email.com", "abcd1234", 15);
	private static LoginMember member3 = new LoginMember(3L, "abc@email.com", "abcd1234", 7);
	private Map<Long, Station> stations;
	private List<Line> lines;
	private SubwayPath subwayPath;

	private static Stream<Arguments> provideMembers() {
		return Stream.of(
			Arguments.of(member1, 1450),
			Arguments.of(member2, 880),
			Arguments.of(member3, 550)
		);
	}

	@BeforeEach
	void setUp() {
		stations = new HashMap<>();
		stations.put(1L, TestObjectUtils.createStation(1L, "교대역"));
		stations.put(2L, TestObjectUtils.createStation(2L, "강남역"));
		stations.put(3L, TestObjectUtils.createStation(3L, "양재역"));
		stations.put(4L, TestObjectUtils.createStation(4L, "남부터미널역"));

		Line line1 = TestObjectUtils.createLine(1L, "2호선", "GREEN", 0);
		line1.addLineStation(new LineStation(1L, null, 0, 0));
		LineStation lineStation2 = new LineStation(2L, 1L, 2, 2);
		line1.addLineStation(new LineStation(2L, 1L, 2, 2));

		Line line2 = TestObjectUtils.createLine(2L, "신분당선", "RED", 100);
		line2.addLineStation(new LineStation(2L, null, 0, 0));
		line2.addLineStation(new LineStation(3L, 2L, 2, 1));

		Line line3 = TestObjectUtils.createLine(3L, "3호선", "ORANGE", 200);
		line3.addLineStation(new LineStation(1L, null, 0, 0));
		LineStation lineStation6 = new LineStation(4L, 1L, 1, 2);
		LineStation lineStation7 = new LineStation(3L, 4L, 2, 2);
		line3.addLineStation(lineStation6);
		line3.addLineStation(lineStation7);

		lines = Lists.newArrayList(line1, line2, line3);

		List<LineStationEdge> lineStations = Lists.newArrayList(
			new LineStationEdge(lineStation6, line3.getId()),
			new LineStationEdge(lineStation7, line3.getId())
		);
		subwayPath = new SubwayPath(lineStations);
	}

	@DisplayName("거리에 따른 요금 계산")
	@ParameterizedTest
	@CsvSource({"3, 1450", "25, 1750", "68, 2550"})
	void calculateFare(int distance, int result) {
		assertThat(subwayPath.calculateFare(member1, distance, lines)).isEqualTo(result);
	}

	@DisplayName("연령에 따른 요금 계산")
	@ParameterizedTest
	@MethodSource("provideMembers")
	void calculateFare(LoginMember member, int result) {
		assertThat(subwayPath.calculateFare(member, 3, lines)).isEqualTo(result);
	}
}