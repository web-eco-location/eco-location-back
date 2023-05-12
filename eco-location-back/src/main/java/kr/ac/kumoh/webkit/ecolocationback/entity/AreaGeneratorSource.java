package kr.ac.kumoh.webkit.ecolocationback.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table(name = "area_generator_source")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class AreaGeneratorSource {
	@Id
    @Column(name = "area_generator_source_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private String srcNucl;

    @Column(nullable = false)
    private String srcBcoal;

    @Column(nullable = false)
    private String srcHcoal;

    @Column(nullable = false)
    private String srcOil;

    @Column(nullable = false)
    private String srcLng;

    @Column(nullable = false)
    private String srcPump;

    @Column(nullable = false)
    private String srcFuelcell;

    @Column(nullable = false)
    private String srcCoalgas;

    @Column(nullable = false)
    private String srcSolar;

    @Column(nullable = false)
    private String srcWind;

    @Column(nullable = false)
    private String srcWater;

    @Column(nullable = false)
    private String srcSea;

    @Column(nullable = false)
    private String srcBio;

    @Column(nullable = false)
    private String srcWaste;

    @Column(nullable = false)
    private String srcRecycleSum;

    @Column(nullable = false)
    private String srcOther;

    @Column(nullable = false)
    private String srcAll;

    @Column(nullable = false)
    private String recyclePercent;

    @Builder
    public AreaGeneratorSource(String time, String area, String srcNucl, String srcBcoal, String srcHcoal, String srcOil,
                        String srcLng, String srcPump, String srcFuelcell, String srcCoalgas, String srcSolar,
                        String srcWind, String srcWater, String srcSea, String srcBio, String srcWaste,
                        String srcRecycleSum, String srcOther, String srcAll, String recyclePercent) {
        this.time = time;
        this.area = area;
        this.srcNucl = srcNucl;
        this.srcBcoal = srcBcoal;
        this.srcHcoal = srcHcoal;
        this.srcOil = srcOil;
        this.srcLng = srcLng;
        this.srcPump = srcPump;
        this.srcFuelcell = srcFuelcell;
        this.srcCoalgas = srcCoalgas;
        this.srcSolar = srcSolar;
        this.srcWind = srcWind;
        this.srcWater = srcWater;
        this.srcSea = srcSea;
        this.srcBio = srcBio;
        this.srcWaste = srcWaste;
        this.srcRecycleSum = srcRecycleSum;
        this.srcOther = srcOther;
        this.srcAll = srcAll;
        this.recyclePercent = recyclePercent;
    }
}
