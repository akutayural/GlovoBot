package com.example.glovobot.Model;

import java.util.List;

public class ActiveDeliveries {

    private Object dedicatedSlot;
    private Object cashBalance;
    private boolean newSelfReassignmentEnabled;
    private boolean hideSelfReassignedDeliveries;
    private boolean newSelfReassignmentTimerEnabled;
    private Object nextSlot;
    private Object nextSlotSelfKickOutNotStarted;
    private List<Delivery> deliveries;
    private boolean isLastReassignment;
    private List<Object> prepositioning;
    private boolean inCoolOff;
    private int remainingCoolOffDuration;
    private int totalCoolOffDuration;
    private EmptyDeliveryReason emptyDeliveryReason;

    public ActiveDeliveries(Object dedicatedSlot, Object cashBalance, boolean newSelfReassignmentEnabled, boolean hideSelfReassignedDeliveries, boolean newSelfReassignmentTimerEnabled, Object nextSlot, Object nextSlotSelfKickOutNotStarted, List<Delivery> deliveries, boolean isLastReassignment, List<Object> prepositioning, boolean inCoolOff, int remainingCoolOffDuration, int totalCoolOffDuration, EmptyDeliveryReason emptyDeliveryReason) {
        this.dedicatedSlot = dedicatedSlot;
        this.cashBalance = cashBalance;
        this.newSelfReassignmentEnabled = newSelfReassignmentEnabled;
        this.hideSelfReassignedDeliveries = hideSelfReassignedDeliveries;
        this.newSelfReassignmentTimerEnabled = newSelfReassignmentTimerEnabled;
        this.nextSlot = nextSlot;
        this.nextSlotSelfKickOutNotStarted = nextSlotSelfKickOutNotStarted;
        this.deliveries = deliveries;
        this.isLastReassignment = isLastReassignment;
        this.prepositioning = prepositioning;
        this.inCoolOff = inCoolOff;
        this.remainingCoolOffDuration = remainingCoolOffDuration;
        this.totalCoolOffDuration = totalCoolOffDuration;
        this.emptyDeliveryReason = emptyDeliveryReason;
    }

    public Object getDedicatedSlot() {
        return dedicatedSlot;
    }

    public void setDedicatedSlot(Object dedicatedSlot) {
        this.dedicatedSlot = dedicatedSlot;
    }

    public Object getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(Object cashBalance) {
        this.cashBalance = cashBalance;
    }

    public boolean isNewSelfReassignmentEnabled() {
        return newSelfReassignmentEnabled;
    }

    public void setNewSelfReassignmentEnabled(boolean newSelfReassignmentEnabled) {
        this.newSelfReassignmentEnabled = newSelfReassignmentEnabled;
    }

    public boolean isHideSelfReassignedDeliveries() {
        return hideSelfReassignedDeliveries;
    }

    public void setHideSelfReassignedDeliveries(boolean hideSelfReassignedDeliveries) {
        this.hideSelfReassignedDeliveries = hideSelfReassignedDeliveries;
    }

    public boolean isNewSelfReassignmentTimerEnabled() {
        return newSelfReassignmentTimerEnabled;
    }

    public void setNewSelfReassignmentTimerEnabled(boolean newSelfReassignmentTimerEnabled) {
        this.newSelfReassignmentTimerEnabled = newSelfReassignmentTimerEnabled;
    }

    public Object getNextSlot() {
        return nextSlot;
    }

    public void setNextSlot(Object nextSlot) {
        this.nextSlot = nextSlot;
    }

    public Object getNextSlotSelfKickOutNotStarted() {
        return nextSlotSelfKickOutNotStarted;
    }

    public void setNextSlotSelfKickOutNotStarted(Object nextSlotSelfKickOutNotStarted) {
        this.nextSlotSelfKickOutNotStarted = nextSlotSelfKickOutNotStarted;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public boolean isLastReassignment() {
        return isLastReassignment;
    }

    public void setLastReassignment(boolean lastReassignment) {
        isLastReassignment = lastReassignment;
    }

    public List<Object> getPrepositioning() {
        return prepositioning;
    }

    public void setPrepositioning(List<Object> prepositioning) {
        this.prepositioning = prepositioning;
    }

    public boolean isInCoolOff() {
        return inCoolOff;
    }

    public void setInCoolOff(boolean inCoolOff) {
        this.inCoolOff = inCoolOff;
    }

    public int getRemainingCoolOffDuration() {
        return remainingCoolOffDuration;
    }

    public void setRemainingCoolOffDuration(int remainingCoolOffDuration) {
        this.remainingCoolOffDuration = remainingCoolOffDuration;
    }

    public int getTotalCoolOffDuration() {
        return totalCoolOffDuration;
    }

    public void setTotalCoolOffDuration(int totalCoolOffDuration) {
        this.totalCoolOffDuration = totalCoolOffDuration;
    }

    public EmptyDeliveryReason getEmptyDeliveryReason() {
        return emptyDeliveryReason;
    }

    public void setEmptyDeliveryReason(EmptyDeliveryReason emptyDeliveryReason) {
        this.emptyDeliveryReason = emptyDeliveryReason;
    }
}


/*
{
    "activeDeliveries": {
        "dedicatedSlot": null,
        "cashBalance": null,
        "newSelfReassignmentEnabled": true,
        "hideSelfReassignedDeliveries": true,
        "newSelfReassignmentTimerEnabled": true,
        "nextSlot": null,
        "nextSlotSelfKickOutNotStarted": null,
        "deliveries": [
            {
                "id": 583492938,
                "orderId": 100226543147,
                "orderIds": [
                    100226543147
                ],
                "banner": null,
                "urn": "URN",
                "points": [
                    {
                        "isDelivery": false,
                        "latitude": 40.44352995,
                        "longitude": -3.6739585,
                        "label": "C. Juan Bautista de Toledo, XX",
                        "obfuscated": true,
                        "banner": null
                    },
                    {
                        "isDelivery": true,
                        "latitude": 40.4379534,
                        "longitude": -3.6691139,
                        "label": "Calle de Francisco Remiro, XX",
                        "obfuscated": true,
                        "banner": null
                    }
                ],
                "totalDistance": "2.0 KM",
                "totalDistanceDescription": null,
                "totalCompensation": "2,70 €",
                "compensationInMinimumRep": 270,
                "currencyDecimals": 2,
                "distanceInMinimumRep": 2004,
                "bonusCoefficient": "1.40x",
                "courierPaymentBreakdown": [],
                "isBoosted": false,
                "distanceBasedEarnings": "STANDARD_EARNINGS",
                "maxBillAmount": 3000.0,
                "instructions": [
                    {
                        "type": "PICKUP",
                        "label": "C. Juan Bautista de Toledo, XX"
                    },
                    {
                        "type": "DELIVERY",
                        "label": "Calle de Francisco Remiro, XX"
                    }
                ],
                "paymentHints": [
                    "DONT_PAY",
                    "DONT_ASK_FOR_PRODUCTS",
                    "CUSTOMER_PAYS_WITH_CARD"
                ],
                "numberOfOrders": 1,
                "deliveryCode": "#MQEWXW3X",
                "deliveryType": "PURCHASE",
                "isNew": false,
                "reassignmentRequested": false,
                "secondsToExpire": null,
                "acceptanceTimeStamp": null,
                "isStarted": false,
                "canBeSelfReassigned": true,
                "startBtnText": "Start",
                "activationTime": 1699228606000,
                "version": 1699229199423,
                "newOrderFlowEnabled": true,
                "acceptanceTimer": {
                    "totalDurationSeconds": 122,
                    "remainingDurationSeconds": 113
                },
                "reassignmentTimeLeftInSeconds": 113,
                "pickupPointETA": null,
                "returnData": null,
                "notification": null,
                "richNavigationAPIEnabled": true,
                "benefits": []
            }
        ],
        "isLastReassignment": false,
        "prepositioning": [],
        "inCoolOff": false,
        "remainingCoolOffDuration": 0,
        "totalCoolOffDuration": 0,
        "emptyDeliveryReason": null
    },
    "currentDaySummary": {
        "id": 116502471,
        "earnings": "0,89 €"
    }
}
 */
