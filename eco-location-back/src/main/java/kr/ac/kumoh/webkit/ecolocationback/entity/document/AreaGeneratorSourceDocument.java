package kr.ac.kumoh.webkit.ecolocationback.entity.document;

import kr.ac.kumoh.webkit.ecolocationback.entity.AreaGeneratorSource;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "area_generator_source_index")
public class AreaGeneratorSourceDocument {

    @Id
    private String id;

    @Field(type = FieldType.Date)
    private LocalDate date;

    @Field(type = FieldType.Text)
    private String area;

    @Field(type = FieldType.Double)
    private double srcNucl;

    @Field(type = FieldType.Double)
    private double srcBcoal;

    @Field(type = FieldType.Double)
    private double srcHcoal;

    @Field(type = FieldType.Double)
    private double srcOil;

    @Field(type = FieldType.Double)
    private double srcLng;

    @Field(type = FieldType.Double)
    private double srcPump;

    @Field(type = FieldType.Double)
    private double srcFuelcell;

    @Field(type = FieldType.Double)
    private double srcCoalgas;

    @Field(type = FieldType.Double)
    private double srcSolar;

    @Field(type = FieldType.Double)
    private double srcWind;

    @Field(type = FieldType.Double)
    private double srcWater;

    @Field(type = FieldType.Double)
    private double srcSea;

    @Field(type = FieldType.Double)
    private double srcBio;

    @Field(type = FieldType.Double)
    private double srcWaste;

    @Field(type = FieldType.Double)
    private double srcRecycleSum;

    @Field(type = FieldType.Double)
    private double srcOther;

    @Field(type = FieldType.Double)
    private double srcAll;

    @Field(type = FieldType.Double)
    private double recyclePercent;

    public static AreaGeneratorSourceDocument fromEntity(AreaGeneratorSource entity) {
        return AreaGeneratorSourceDocument.builder()
                .id(entity.getId().toString())
                .date(entity.getDate())
                .area(entity.getArea())
                .srcNucl(entity.getSrcNucl())
                .srcBcoal(entity.getSrcBcoal())
                .srcHcoal(entity.getSrcHcoal())
                .srcOil(entity.getSrcOil())
                .srcLng(entity.getSrcLng())
                .srcPump(entity.getSrcPump())
                .srcFuelcell(entity.getSrcFuelcell())
                .srcCoalgas(entity.getSrcCoalgas())
                .srcSolar(entity.getSrcSolar())
                .srcWind(entity.getSrcWind())
                .srcWater(entity.getSrcWater())
                .srcSea(entity.getSrcSea())
                .srcBio(entity.getSrcBio())
                .srcWaste(entity.getSrcWaste())
                .srcRecycleSum(entity.getSrcRecycleSum())
                .srcOther(entity.getSrcOther())
                .srcAll(entity.getSrcAll())
                .recyclePercent(entity.getRecyclePercent())
                .build();
    }
}
