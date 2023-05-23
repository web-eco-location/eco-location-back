package kr.ac.kumoh.webkit.ecolocationback.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private LocalDate date;

    @Column(nullable = false)
    private String area;

    @Column(nullable = false)
    private double srcNucl;

    @Column(nullable = false)
    private double srcBcoal;

    @Column(nullable = false)
    private double srcHcoal;

    @Column(nullable = false)
    private double srcOil;

    @Column(nullable = false)
    private double srcLng;

    @Column(nullable = false)
    private double srcPump;

    @Column(nullable = false)
    private double srcFuelcell;

    @Column(nullable = false)
    private double srcCoalgas;

    @Column(nullable = false)
    private double srcSolar;

    @Column(nullable = false)
    private double srcWind;

    @Column(nullable = false)
    private double srcWater;

    @Column(nullable = false)
    private double srcSea;

    @Column(nullable = false)
    private double srcBio;

    @Column(nullable = false)
    private double srcWaste;

    @Column(nullable = false)
    private double srcRecycleSum;

    @Column(nullable = false)
    private double srcOther;

    @Column(nullable = false)
    private double srcAll;

    @Column(nullable = false)
    private double recyclePercent;

    @Builder
    public AreaGeneratorSource(LocalDate date, String area, double srcNucl, double srcBcoal, double srcHcoal, double srcOil,
                               double srcLng, double srcPump, double srcFuelcell, double srcCoalgas, double srcSolar,
                               double srcWind, double srcWater, double srcSea, double srcBio, double srcWaste,
                               double srcRecycleSum, double srcOther, double srcAll, double recyclePercent) {
        this.date = date;
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
