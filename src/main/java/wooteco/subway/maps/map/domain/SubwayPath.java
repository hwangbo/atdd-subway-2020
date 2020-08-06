package wooteco.subway.maps.map.domain;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import wooteco.subway.maps.line.domain.Line;
import wooteco.subway.members.member.domain.LoginMember;

public class SubwayPath {
    public static final int BASIC_FARE = 1250;
    public static final int MAX_DISTANCE_FARE_UNDER_FIFTY = 800;
    private List<LineStationEdge> lineStationEdges;

    public SubwayPath(List<LineStationEdge> lineStationEdges) {
        this.lineStationEdges = lineStationEdges;
    }

    public List<LineStationEdge> getLineStationEdges() {
        return lineStationEdges;
    }

    public List<Long> extractStationId() {
        List<Long> stationIds = Lists.newArrayList(lineStationEdges.get(0).getLineStation().getPreStationId());
        stationIds.addAll(lineStationEdges.stream()
                .map(it -> it.getLineStation().getStationId())
                .collect(Collectors.toList()));

        return stationIds;
    }

    public int calculateDuration() {
        return lineStationEdges.stream().mapToInt(it -> it.getLineStation().getDuration()).sum();
    }

    public int calculateDistance() {
        return lineStationEdges.stream().mapToInt(it -> it.getLineStation().getDistance()).sum();
    }

    public int calculateFare(LoginMember member, int distance, List<Line> lines) {
        int maxExtraFare = getMaxExtraFare(lines);

        if (distance < 10) {
            return member.discountFare(BASIC_FARE + maxExtraFare);
        }
        if (distance < 50) {
            return member.discountFare(BASIC_FARE + maxExtraFare + calculateOverFare(distance - 10));
        }
        return member.discountFare(
            BASIC_FARE + maxExtraFare + calculateOverFiftyFare(distance - 50) + MAX_DISTANCE_FARE_UNDER_FIFTY);
    }

    private int getMaxExtraFare(List<Line> lines) {
        OptionalInt maxExtraFare = lines.stream().mapToInt(Line::getExtraFare).max();
        if (maxExtraFare.isPresent()) {
            return maxExtraFare.getAsInt();
        }
        throw new IllegalArgumentException("최대 추가 요금이 없습니다.");
    }

    private int calculateOverFare(int distance) {
        if (distance == 0) {
            return 0;
        }
        return (int)((Math.ceil((distance - 1) / 5) + 1) * 100);
    }

    private int calculateOverFiftyFare(int distance) {
        if (distance == 0) {
            return 0;
        }
        return (int)((Math.ceil((distance - 1) / 8) + 1) * 100);
    }
}
