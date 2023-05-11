package kr.ac.kumoh.webkit.ecolocationback.dto.response;

import kr.ac.kumoh.webkit.ecolocationback.entity.Generator;
import lombok.Data;

@Data
public class GeneratorCountDto {
    // 가스 에너지
    private Long gasGeneratorCount = 0L;
    // 바이오 에너지
    private Long bioGeneratorCount = 0L;
    // 석유 에너지
    private Long oilGeneratorCount = 0L;
    // 석탄 에너지
    private Long coalGeneratorCount = 0L;
    // 석탄가스화 에너지
    private Long coalGasificationGeneratorCount = 0L;
    // 양수 에너지
    private Long tidalGeneratorCount = 0L;
    // 수력 에너지
    private Long hydroGeneratorCount = 0L;
    // 연로전지 에너지
    private Long fuelCellGeneratorCount = 0L;
    // 원자력 에너지
    private Long nuclearGeneratorCount = 0L;
    // 태양 에너지
    private Long solarGeneratorCount = 0L;
    // 풍력 에너지
    private Long windGeneratorCount = 0L;
    // 수력 에너지
    private Long oceanGeneratorCount = 0L;

    public void count(Generator generator) {
        String powerSource = generator.getPowerSource();
        if (powerSource.equals("양수")) {
            setTidalGeneratorCount(getTidalGeneratorCount() + 1);
        } else if (powerSource.equals("연료전지")) {
            setFuelCellGeneratorCount(getFuelCellGeneratorCount() + 1);
        } else if (powerSource.equals("원자력")) {
            setNuclearGeneratorCount(getNuclearGeneratorCount() + 1);
        } else if (powerSource.equals("태양에너지")) {
            setSolarGeneratorCount(getSolarGeneratorCount() + 1);
        } else if (powerSource.equals("풍력에너지")) {
            setWindGeneratorCount(getWindGeneratorCount() + 1);
        } else if (powerSource.equals("해양에너지")) {
            setOceanGeneratorCount(getOceanGeneratorCount() + 1);
        } else if (powerSource.equals("수력에너지")) {
            setHydroGeneratorCount(getHydroGeneratorCount() + 1);
        } else if (powerSource.equals("가스")) {
            setGasGeneratorCount(getGasGeneratorCount() + 1);
        } else if (powerSource.equals("바이오에너지")) {
            setBioGeneratorCount(getBioGeneratorCount() + 1);
        } else if (powerSource.equals("석유")) {
            setOilGeneratorCount(getOilGeneratorCount() + 1);
        } else if (powerSource.equals("석탄")) {
            setFuelCellGeneratorCount(getFuelCellGeneratorCount() + 1);
        } else if (powerSource.equals("석탄가스화에너지")) {
            setCoalGasificationGeneratorCount(getCoalGeneratorCount() + 1);
        }
    }
}
