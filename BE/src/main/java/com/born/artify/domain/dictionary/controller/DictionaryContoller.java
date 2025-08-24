package com.born.artify.domain.dictionary.controller;

import com.born.artify.domain.dictionary.dto.CreateDictReqDTO;
import com.born.artify.domain.dictionary.dto.DictResDTO;
import com.born.artify.domain.dictionary.entity.dictionary;
import com.born.artify.domain.dictionary.service.DictionaryService;
import com.born.artify.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/dict")
public class DictionaryContoller {

    private final DictionaryService dictionaryService;

    public DictionaryContoller(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }


    @GetMapping()
    public ResponseEntity<Map<String, Object>> getDictList() {
        long userId = SecurityUtil.getCurrentUserIdAsInt();

        List<dictionary> dictList = dictionaryService.getDictionary(userId);

        List<DictResDTO> dictDTOList = dictList.stream()
                .map(dict -> new DictResDTO(dict.getDictId(), dict.getFrom_key(), dict.getTo_value(), dict.getCreatedAt()))
                .toList();

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "dictionary 조회 성공");
        result.put("data", dictDTOList);

        return ResponseEntity.ok().body(result);
    }


    @PostMapping()
    public ResponseEntity<Map<String, Object>> createDictionary(@RequestBody CreateDictReqDTO dto) {
        long userId = SecurityUtil.getCurrentUserIdAsInt();

        dictionary dict = dictionaryService.createDict(dto, userId);

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "dictionary 등록 성공");
        result.put("data", dict);

        return ResponseEntity.ok().body(result);
    }



    @DeleteMapping("/{dictId}")
    public ResponseEntity<Map<String, Object>> deleteUserOpenAIKey(@PathVariable long dictId) {
        long userId = SecurityUtil.getCurrentUserIdAsInt();

        long deletedRow = dictionaryService.deleteDictionary(dictId);

        Map<String, Object> result = new HashMap<>();

        if(deletedRow == 1)
        {
            result.put("status", 200);
            result.put("msg", "OpenAi API key 삭제 성공");
        }else{
            result.put("status", 200);
            result.put("msg", "OpenAi API key 삭제 실패");
        }


        return ResponseEntity.ok().body(result);
    }

}
