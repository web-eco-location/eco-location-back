package kr.ac.kumoh.webkit.ecolocationback.entity.document;

import kr.ac.kumoh.webkit.ecolocationback.entity.EnergyPotential;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Document(indexName = "energy_potentials_index")
public class EnergyPotentialDocument {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String powerType;

    @Field(type = FieldType.Keyword)
    private String areaName;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute)
    private LocalDateTime createTime;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute)
    private LocalDateTime forecastTime;

    @Field(type = FieldType.Long)
    private Long leadTime;

    @Field(type = FieldType.Double)
    private double forecastEnergyPotential;

    @Field(type = FieldType.Double)
    private double forecastCapacity;

    public static EnergyPotentialDocument fromEntity(EnergyPotential energyPotential) {
        return EnergyPotentialDocument.builder()
                .id(String.valueOf(energyPotential.getId()))
                .powerType(energyPotential.getPowerType())
                .areaName(energyPotential.getAreaName())
                .createTime(energyPotential.getCreateTime())
                .forecastTime(energyPotential.getForecastTime())
                .leadTime(energyPotential.getLeadTime())
                .forecastEnergyPotential(energyPotential.getForecastEnergyPotential())
                .forecastCapacity(energyPotential.getForecastCapacity())
                .build();
    }
}

