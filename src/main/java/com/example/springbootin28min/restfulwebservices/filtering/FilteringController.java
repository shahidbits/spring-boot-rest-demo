package com.example.springbootin28min.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    // field1 & field2
    @GetMapping(path = "/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("F1", "F2", "F3");
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mapping.setFilters(filters);

        return mapping;
    }

    // field2 & field3
    @GetMapping(path = "/filtering-list")
    public MappingJacksonValue retrieveSomeBeanList() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("F1", "F2", "F3"), new SomeBean("G1", "G2", "G3"));
        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mapping.setFilters(filters);

        return mapping;
    }
}
