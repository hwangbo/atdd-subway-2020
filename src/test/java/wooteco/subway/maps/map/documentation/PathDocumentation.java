package wooteco.subway.maps.map.documentation;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.web.context.WebApplicationContext;

import wooteco.subway.common.documentation.Documentation;
import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.maps.map.ui.MapController;
import wooteco.subway.maps.station.dto.StationResponse;

@WebMvcTest(controllers = {MapController.class})
public class PathDocumentation extends Documentation {
	@MockBean
	private MapService mapService;

	@BeforeEach
	public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
		super.setUp(context, restDocumentation);
	}

	@Test
	void findPath() {
		StationResponse 광교중앙역 = new StationResponse(1L, "광교중앙역", LocalDateTime.now(), LocalDateTime.now());
		StationResponse 잠실역 = new StationResponse(2L, "잠실역", LocalDateTime.now(), LocalDateTime.now());
		PathResponse pathResponse = new PathResponse(Arrays.asList(광교중앙역, 잠실역), 2, 3, 100);
		when(mapService.findPath(any(), anyLong(), anyLong(), any())).thenReturn(pathResponse);

		given().log().all().
			accept(MediaType.APPLICATION_JSON_VALUE).
			when().
			get("/paths?source={sourceId}&target={targetId}&type={type}", 1L, 2L, "DISTANCE").
			then().
			log().all().
			apply(document("maps/find-path",
				getDocumentRequest(),
				getDocumentResponse(),
				responseFields(
					fieldWithPath("stations").type(JsonFieldType.ARRAY).description("경로 목록"),
					fieldWithPath("stations.[].id").type(JsonFieldType.NUMBER).description("경로역 id"),
					fieldWithPath("stations.[].name").type(JsonFieldType.STRING).description("경로역 이름"),
					fieldWithPath("duration").type(JsonFieldType.NUMBER).description("총 소요시간"),
					fieldWithPath("distance").type(JsonFieldType.NUMBER).description("총 거리"),
					fieldWithPath("fare").type(JsonFieldType.NUMBER).description("총 요금")
				))).
			extract();
	}
}
