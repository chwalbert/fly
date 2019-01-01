package com.cloud.fly.content.core.model.search;

/**
 * @Auther: chw
 * @Date: 2018/12/20 00:04
 * @Description:
 */
public class SegmentElement {
    /**
     * 航司
     */
    private String carrier;

    /**
     * 航班号，如:CA1234。航班号数字丌足 四位，补足四位，如 CZ6 需返回 CZ0006
     */
    private String flightNumber;

    /**
     * 出发机场 IATA 三字码
     */
    private String depAirport;

    /**
     * 出发航站楼，使用简写，例如 T1
     */
    private String depTerminal;

    /**
     * 起飞日期时间，格式: YYYYMMDDHHMM 例:201702041305 表示 2017 年 2 月 04 日 13 时 5 分
     */
    private String depTime;

    /**
     * 到达机场 IATA 三字码
     */
    private String arrAirport;

    /**
     * 抵达航站楼，使用简写，例如 T1
     */
    private String arrTerminal;

    /**
     * 到达日期时间，格式: YYYYMMDDHHMM 例:201702041305 表示 2017 年 2 月 04 日 13 时 5 分
     */
    private String arrTime;

    /**
     * 经停机场，/分隔机场三字码(如果有经 停，必传)
     */
    private String stopAirports;

    /**
     * 代码共享标识(true 代码共享/false 非代 码共享)
     */
    private Boolean codeShare;
    /**
     * 实际承运航司，当 codeShare=true 时， operatingCarrier 丌能为空
     */
    private String operatingCarrier;


    /**
     * 实际承运航班号,如:CA1234。航班号数 字丌足四位，补足四位，如 CZ6 需返 回 CZ0006
     */
    private String operatingFlightNo;
    /**
     * 舱位
     */
    private String cabin;

    /**
     * 当前可售舱位个数，用于判断库存是否 满足客户需求
     */
    private Integer cabinCount;

    /**
     * 舱等 头等:F 商务:C 超经:S 经济:Y
     */
    private String cabinGrade;


    /**
     * 机型，IATA 标准 3 字码,幵过滤下列机型 运价信息
     * BUS|ICE|LCH|LMO|MTL|RFS|TGV|THS| THT|TRN|TSL
     */
    private String aircraftCode;

    /**
     * 飞行时长(分钟)，通过时差转换后的结 果，无法提供时请赋值 0
     */
    private Integer duration;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepAirport() {
        return depAirport;
    }

    public void setDepAirport(String depAirport) {
        this.depAirport = depAirport;
    }

    public String getDepTerminal() {
        return depTerminal;
    }

    public void setDepTerminal(String depTerminal) {
        this.depTerminal = depTerminal;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrAirport() {
        return arrAirport;
    }

    public void setArrAirport(String arrAirport) {
        this.arrAirport = arrAirport;
    }

    public String getArrTerminal() {
        return arrTerminal;
    }

    public void setArrTerminal(String arrTerminal) {
        this.arrTerminal = arrTerminal;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getStopAirports() {
        return stopAirports;
    }

    public void setStopAirports(String stopAirports) {
        this.stopAirports = stopAirports;
    }

    public Boolean getCodeShare() {
        return codeShare;
    }

    public void setCodeShare(Boolean codeShare) {
        this.codeShare = codeShare;
    }

    public String getOperatingCarrier() {
        return operatingCarrier;
    }

    public void setOperatingCarrier(String operatingCarrier) {
        this.operatingCarrier = operatingCarrier;
    }

    public String getOperatingFlightNo() {
        return operatingFlightNo;
    }

    public void setOperatingFlightNo(String operatingFlightNo) {
        this.operatingFlightNo = operatingFlightNo;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public Integer getCabinCount() {
        return cabinCount;
    }

    public void setCabinCount(Integer cabinCount) {
        this.cabinCount = cabinCount;
    }

    public String getCabinGrade() {
        return cabinGrade;
    }

    public void setCabinGrade(String cabinGrade) {
        this.cabinGrade = cabinGrade;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "SegmentElement{" +
                "carrier='" + carrier + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", depAirport='" + depAirport + '\'' +
                ", depTerminal='" + depTerminal + '\'' +
                ", depTime='" + depTime + '\'' +
                ", arrAirport='" + arrAirport + '\'' +
                ", arrTerminal='" + arrTerminal + '\'' +
                ", arrTime='" + arrTime + '\'' +
                ", stopAirports='" + stopAirports + '\'' +
                ", codeShare=" + codeShare +
                ", operatingCarrier='" + operatingCarrier + '\'' +
                ", operatingFlightNo='" + operatingFlightNo + '\'' +
                ", cabin='" + cabin + '\'' +
                ", cabinCount=" + cabinCount +
                ", cabinGrade='" + cabinGrade + '\'' +
                ", aircraftCode='" + aircraftCode + '\'' +
                ", duration=" + duration +
                '}';
    }
}
