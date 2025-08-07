package spring.cloud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.cloud.dtos.ErrorDto;
import spring.cloud.dtos.TestDto;
import spring.cloud.exceptions.InvalidOperationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private final Map<Long, TestDto> testDtos = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<TestDto>> getTest() {

        List<TestDto> tests = new ArrayList<>(testDtos.values());

        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTest(@PathVariable Long id) {
        TestDto testDto = testDtos.getOrDefault(id, null);

        if (testDto == null) {
            throw new InvalidOperationException("Test not found");
        }

        return ResponseEntity.ok(testDto);
    }


    @PostMapping
    public ResponseEntity<TestDto> postTest(
            @RequestBody TestDto testDto) {
        testDtos.put(testDto.id(), testDto);
        return ResponseEntity.status(CREATED).body(testDto);
    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidOperationException ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }
}
