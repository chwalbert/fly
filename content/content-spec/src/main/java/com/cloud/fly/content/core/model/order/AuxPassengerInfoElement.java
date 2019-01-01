package com.cloud.fly.content.core.model.order;

/**
 * @Auther: chw
 * @Date: 2018/12/21 00:09
 * @Description:
 */
public class AuxPassengerInfoElement {
    /**
     * 同程乘机人 ID
     */
    private String passengerID;

    /**
     * LastName 姓
     */
    private String lastName;


    /**
     * FirstName 名
     */
    private String firstName;

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
