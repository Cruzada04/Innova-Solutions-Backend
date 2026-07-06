package com.upc.innovasolutionsbackend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaestroDashboardStatsDTO {
    private long totalAlumnos;
    private long totalTemas;
    private long totalLecciones;
    private long totalFlashcards;
}
