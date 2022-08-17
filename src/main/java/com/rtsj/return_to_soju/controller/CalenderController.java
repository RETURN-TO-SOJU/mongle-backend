package com.rtsj.return_to_soju.controller;

import com.rtsj.return_to_soju.common.JwtProvider;
import com.rtsj.return_to_soju.model.dto.request.WriteDiaryDto;
import com.rtsj.return_to_soju.model.dto.response.CalenderByDayDto;
import com.rtsj.return_to_soju.model.dto.response.CalenderBetweenMonthResponseDto;
import com.rtsj.return_to_soju.model.dto.response.SentenceByEmotionWithDayDto;
import com.rtsj.return_to_soju.model.enums.Emotion;
import com.rtsj.return_to_soju.service.CalenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Tag(name = "Calender Controller", description = "Calender controller desc")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CalenderController {

    // 만들까 말까~
//    @GetMapping("/calender/{calenderId}")
    private final CalenderService calenderService;
    private final JwtProvider jwtProvider;

    @Operation(
            summary = "월 별 캘린더 데이터 조회 api",
            description = "query parameter로 필요한 년도 및 월을 받아 해당 월의 감정 및 주제들을 반환한다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "요청 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CalenderBetweenMonthResponseDto.class))))
    })
    @GetMapping("/calender/{year}/{start}/{end}")
    public ResponseEntity<List<CalenderBetweenMonthResponseDto>> getCalenderByMonth(
            HttpServletRequest request,
            @PathVariable(name = "year") String year,
            @PathVariable(name = "start") String start,
            @PathVariable(name = "end") String end) {
        Long userId = jwtProvider.getUserIdByToken(request);
        List<CalenderBetweenMonthResponseDto> emotionBetweenMonth = calenderService.findEmotionBetweenMonth(userId, year, start, end);
        return ResponseEntity.ok().body(emotionBetweenMonth);
    }



    @Operation(
            summary = "일 별 캘린더 데이터 조회 api",
            description = "path variable로 필요한 년도, 월, 일(22/07/26)을 입력 받아 해당 일의 필요한 데이터를 조회한다."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "요청 성공",
                            content = @Content(schema = @Schema(implementation = CalenderByDayDto.class)))
            }
    )
    @GetMapping("/calender/{year}/{month}/{day}")
    public String getCalenderByDay(
            @PathVariable(name = "year") String year,
            @PathVariable(name = "month") String month,
            @PathVariable(name = "day") String day
    ) {
        return null;
    }


    @Operation(
            summary = "일기 작성 api",
            description = "path variable로 필요한 년도, 월, 일(22/07/26)을 입력 받은 후 body로 일기 내용을 입력받아 일기를 작성한다."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "등록 성공")
            }
    )
    @PostMapping("/calender/{year}/{month}/{day}/diary")
    public String writeDiary(
            @PathVariable(name = "year") String year,
            @PathVariable(name = "month") String month,
            @PathVariable(name = "day") String day,
            @RequestBody WriteDiaryDto dto
    ) {

        return null;
    }

    @Operation(
            summary = "일자 및 감정별 대화 문장 불러오는 api",
            description = "특정 일의 분석화면에서 감정이를 눌렀을 때 해당하는 대화 문장들을 불러온다."
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "요청 성공 // url = 원본 파일 url",
                    content = @Content(schema = @Schema(implementation = SentenceByEmotionWithDayDto.class)))
            }
    )
    @GetMapping("/calender/{year}/{month}/{day}/sentences")
    public String getEmotionSentence(@RequestParam("emotion") Emotion emotion) {
        return null;
    }
}
