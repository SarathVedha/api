package com.vedha.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "AllApi", name = "AllApiDTO", description = "Get All Apis From Table")
public class AllApiDTO {

    @Schema(name = "currentPage")
    private int currentPage;

    @Schema(name = "currentApiCount")
    private int currentApiCount;

    @Schema(name = "totalPages")
    private int totalPages;

    @Schema(name = "totalApis")
    private int totalApis;

    @Schema(name = "apis")
    private List<ApiDTO> apis;

}
