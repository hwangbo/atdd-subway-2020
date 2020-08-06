package wooteco.subway.maps.map.ui;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.domain.PathType;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.members.member.domain.LoginMember;

public class MapControllerTest {
    @Test
    void findPath() {
		MapService mapService = mock(MapService.class);
		MapController controller = new MapController(mapService);
		when(mapService.findPath(any(), anyLong(), anyLong(), any())).thenReturn(new PathResponse());
		LoginMember member = new LoginMember(1L, "abc@email.com", "abcd1234", 35);

		ResponseEntity<PathResponse> entity = controller.findPath(member, 1L, 2L, PathType.DISTANCE);

		assertThat(entity.getBody()).isNotNull();
	}

    @Test
    void findMap() {
        MapService mapService = mock(MapService.class);
        MapController controller = new MapController(mapService);

        ResponseEntity entity = controller.findMap();

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(mapService).findMap();
    }
}
