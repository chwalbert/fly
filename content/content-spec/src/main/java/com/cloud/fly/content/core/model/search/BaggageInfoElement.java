package com.cloud.fly.content.core.model.search;

import java.util.List;

/**
 * @Auther: chw
 * @Date: 2018/12/19 23:59
 * @Description:
 */
public class BaggageInfoElement {

    /**
     * 是否提供免费行李额:0 表示丌提供，1 表示提供;当 hasBaggage=0 时，
     * baggageRule 要求必须返回空数组
     */
    private Integer hasBaggage;

    //"baggagePieces":1 "baggageAllowance":23 "segmentNum":1
    //表示 A-B 时，共 1 件，每件 23KG;
    //1.多段行程时，需按照航段顺序返回多个 对象
    //2.
    //"baggagePieces":0 "baggageAllowance":Y(Y>0)
    //当 baggageAllowance>0 但 是 baggagePieces=0 时表示行李件数丌限，
    //但总重量丌超过 Y 公斤。
    //3. "baggagePieces":Y(Y>0) "baggageAllowance":0
    //当 baggagePieces >0 但 是 baggageAllowance =0 ,表示总共 Y 件
    //4. baggagePieces":0 "baggageAllowance":0
    //表示该段无免费行李额


    public Integer getHasBaggage() {
        return hasBaggage;
    }

    public void setHasBaggage(Integer hasBaggage) {
        this.hasBaggage = hasBaggage;
    }

    public List<BaggageRuleElement> getBaggageRules() {
        return baggageRules;
    }

    public void setBaggageRules(List<BaggageRuleElement> baggageRules) {
        this.baggageRules = baggageRules;
    }

    private List<BaggageRuleElement> baggageRules;

    @Override
    public String toString() {
        return "BaggageInfoElement{" +
                "hasBaggage=" + hasBaggage +
                ", baggageRules=" + baggageRules +
                '}';
    }
}
