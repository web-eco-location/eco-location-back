package kr.ac.kumoh.webkit.ecolocationback.entity.document;

import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "generators_index")
public class GeneratorDocument {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String companyName;

    @Field(type = FieldType.Keyword)
    private String generatorName;

    @Field(type = FieldType.Keyword)
    private String generatorNum;

    @Field(type = FieldType.Double)
    private double generateAmount;

    @Field(type = FieldType.Keyword)
    private String memberType;

    @Field(type = FieldType.Keyword)
    private String powerSupply;

    @Field(type = FieldType.Keyword)
    private String powerSource;

    @Field(type = FieldType.Keyword)
    private String generationType;

    @Field(type = FieldType.Keyword)
    private String businessType;

    @Field(type = FieldType.Keyword)
    private String wideArea;

    @Field(type = FieldType.Keyword)
    private String detailArea;

    public static GeneratorDocument fromEntity(Generator generator) {
        return GeneratorDocument.builder()
                .id(String.valueOf(generator.getId()))
                .companyName(generator.getCompanyName())
                .generatorName(generator.getGeneratorName())
                .generatorNum(generator.getGeneratorNum())
                .generateAmount(generator.getGenerateAmount())
                .memberType(generator.getMemberType())
                .powerSupply(generator.getPowerSupply())
                .powerSource(generator.getPowerSource())
                .generationType(generator.getGenerationType())
                .businessType(generator.getBusinessType())
                .wideArea(generator.getWideArea())
                .detailArea(generator.getDetailArea())
                .build();
    }
}
