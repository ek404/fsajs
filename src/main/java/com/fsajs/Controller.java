package com.fsajs;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/data")
    public Map<String, Object> get() {
        return DataStorage.data;
    }

    @PutMapping("/data/{id}")
    public void save(@PathVariable String id, @RequestBody Object body) {
        DataStorage.data.put(id, body);
    }

    @GetMapping("/data/search")
    public Map<String, Object> search(@RequestParam Map<String, Object> requestParams) {
        return DataStorage.search(requestParams);
    }

    @DeleteMapping("/data/{id}")
    public void delete(@PathVariable String id) {
        DataStorage.data.remove(id);
    }

    @DeleteMapping("/data")
    public void deleteAll() {
        DataStorage.data.clear();
    }

}
