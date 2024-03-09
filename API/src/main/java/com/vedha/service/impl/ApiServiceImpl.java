package com.vedha.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.vedha.dto.AllApiDTO;
import com.vedha.dto.ApiDTO;
import com.vedha.entity.ApiEntity;
import com.vedha.exception.ApiNotFoundException;
import com.vedha.repository.ApiRepository;
import com.vedha.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

    private final Gson gson;

    private final ApiRepository apiRepository;

    @Override
    public ApiDTO insertNewApi(ApiDTO apiDTO) {

        ApiEntity build = ApiEntity.builder()
                .apiName(apiDTO.getApiName().toUpperCase())
                .apiResTime(apiDTO.getApiResTime())
                .apiResponse(gson.toJson(apiDTO.getApiResponse(), HashMap.class))
                .build();

        ApiEntity save = apiRepository.save(build);

        return ApiDTO.builder()
                .id(save.getId())
                .apiName(save.getApiName())
                .apiResTime(save.getApiResTime())
                .apiResponse(gson.fromJson(save.getApiResponse(), new TypeToken<HashMap<String, Object>>() {}.getType()))
                .build();
    }

    @Override
    public String getApi(String apiName) {

        ApiEntity apiEntity = apiRepository
                .findByapiName(apiName.toUpperCase())
                .orElseThrow(() -> new ApiNotFoundException("Api Not Found : " + apiName.toUpperCase()));

        try {
            TimeUnit.SECONDS.sleep(Long.parseLong(apiEntity.getApiResTime()));
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        return gson.toJson(JsonParser.parseString(apiEntity.getApiResponse()));
    }

    @Override
    public AllApiDTO getAllApis(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<ApiEntity> all = apiRepository.findAll(pageable);

        return AllApiDTO.builder().currentPage(pageNumber)
                .currentApiCount(pageSize)
                .totalApis((int) all.getTotalElements())
                .totalPages(all.getTotalPages())
                .apis(all.getContent().stream()
                        .map(apiEntity -> ApiDTO.builder()
                                .id(apiEntity.getId())
                                .apiName(apiEntity.getApiName())
                                .apiResTime(apiEntity.getApiResTime())
                                .apiResponse(gson.fromJson(apiEntity.getApiResponse(), new TypeToken<HashMap<String, Object>>() {}.getType()))
                                .build())
                        .toList())
                .build();
    }

    @Override
    public ApiDTO getApiById(int id) {

        ApiEntity apiEntity = apiRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Api Not Found : " + id));

        return ApiDTO.builder()
                .id(apiEntity.getId())
                .apiName(apiEntity.getApiName())
                .apiResTime(apiEntity.getApiResTime())
                .apiResponse(gson.fromJson(apiEntity.getApiResponse(), new TypeToken<HashMap<String, Object>>() {}.getType()))
                .build();
    }

    @Override
    public ApiDTO getApiByName(String apiName) {

        ApiEntity apiEntity = apiRepository.findByapiName(apiName.toUpperCase())
                .orElseThrow(() -> new ApiNotFoundException("Api Not Found : " + apiName.toUpperCase()));

        return ApiDTO.builder()
                .id(apiEntity.getId())
                .apiName(apiEntity.getApiName())
                .apiResTime(apiEntity.getApiResTime())
                .apiResponse(gson.fromJson(apiEntity.getApiResponse(), new TypeToken<HashMap<String, Object>>() {}.getType()))
                .build();
    }

    @Override
    public ApiDTO updateApiById(ApiDTO apiDTO) {

        ApiEntity apiEntity = apiRepository.findById(apiDTO.getId())
                .orElseThrow(() -> new ApiNotFoundException("Api Not Found To Update : " + apiDTO.getId()));

        apiEntity.setApiName(apiDTO.getApiName().toUpperCase());
        apiEntity.setApiResTime(apiDTO.getApiResTime());
        apiEntity.setApiResponse(gson.toJson(apiDTO.getApiResponse(), HashMap.class));
        ApiEntity save = apiRepository.save(apiEntity);

        return ApiDTO.builder()
                .id(save.getId())
                .apiName(save.getApiName())
                .apiResTime(save.getApiResTime())
                .apiResponse(gson.fromJson(save.getApiResponse(), new TypeToken<HashMap<String, Object>>() {}.getType()))
                .build();
    }

    @Override
    public ApiDTO updateApiByName(ApiDTO apiDTO) {

        ApiEntity apiEntity = apiRepository.findByapiName(apiDTO.getApiName().toUpperCase())
                .orElseThrow(() -> new ApiNotFoundException("Api Not Found To Update : " + apiDTO.getApiName().toUpperCase()));

        apiEntity.setApiName(apiDTO.getApiName().toUpperCase());
        apiEntity.setApiResTime(apiDTO.getApiResTime());
        apiEntity.setApiResponse(gson.toJson(apiDTO.getApiResponse(), HashMap.class));
        ApiEntity save = apiRepository.save(apiEntity);

        return ApiDTO.builder()
                .id(save.getId())
                .apiName(save.getApiName())
                .apiResTime(save.getApiResTime())
                .apiResponse(gson.fromJson(save.getApiResponse(), new TypeToken<HashMap<String, Object>>() {}.getType()))
                .build();
    }

    @Override
    public String updateAllApisResTime(String apiResTime) {

        String resTime = Optional.of(apiResTime)
                .orElseThrow(() -> new RuntimeException("Api Response Time Should not Null"));
        int count = apiRepository.updateAllApiResTime(resTime);

        return "SuccessFully Updated Apis: ".concat(String.valueOf(count));
    }

    @Override
    public String deleteApiById(int id) {

        ApiEntity apiEntity = apiRepository.findById(id)
                .orElseThrow(() -> new ApiNotFoundException("Api Not Found To Delete : " + id));

        apiRepository.delete(apiEntity);

        return "Api Deleted SuccessFully : " + id;
    }

    @Override
    public String deleteApiByName(String apiName) {

        ApiEntity apiEntity = apiRepository.findByapiName(apiName.toUpperCase())
                .orElseThrow(() -> new ApiNotFoundException("Api Not Found To Delete : " + apiName.toUpperCase()));

        apiRepository.delete(apiEntity);

        return "Api Deleted SuccessFully : " + apiName.toUpperCase();
    }

}
