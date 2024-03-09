package com.vedha.service;

import com.vedha.dto.AllApiDTO;
import com.vedha.dto.ApiDTO;

public interface ApiService {

    ApiDTO insertNewApi(ApiDTO apiDTO);

    String getApi(String apiName);

    AllApiDTO getAllApis(int pageNumber, int pageSize);

    ApiDTO getApiById(int id);

    ApiDTO getApiByName(String apiName);

    ApiDTO updateApiById(ApiDTO apiDTO);

    ApiDTO updateApiByName(ApiDTO apiDTO);

    String updateAllApisResTime(String apiResTime);

    String deleteApiById(int id);

    String deleteApiByName(String apiName);
}
