package com.vedha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "Api", name = "ApiDTO", description = "Holds Apis In Table")
public class ApiDTO {

    @Schema(name = "id", accessMode = Schema.AccessMode.READ_ONLY)
    private int id;

    @Schema(name = "apiName")
    private String apiName;

    @Schema(name = "apiResTime")
    private String apiResTime;

    @Schema(name = "apiResponse")
    private HashMap<String, Object> apiResponse;
}
