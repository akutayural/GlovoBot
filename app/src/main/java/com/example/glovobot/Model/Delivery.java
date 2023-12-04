package com.example.glovobot.Model;

import java.util.List;

public class Delivery {
    private long id;
    private long orderId;
    private List<Long> orderIds;
    private Object banner;
    private String urn;
    private List<Point> points;
    private String totalDistance;
    private String totalDistanceDescription;
    private String totalCompensation;
    private int compensationInMinimumRep;
    private int currencyDecimals;
    private int distanceInMinimumRep;
    private String bonusCoefficient;
    private List<Object> courierPaymentBreakdown;
    private boolean isBoosted;
    private String distanceBasedEarnings;
    private double maxBillAmount;
    private List<Instruction> instructions;
    private List<String> paymentHints;
    private int numberOfOrders;
    private String deliveryCode;
    private String deliveryType;
    private boolean isNew;
    private boolean reassignmentRequested;
    private Integer secondsToExpire;
    private double acceptanceTimeStamp;
    private boolean isStarted;
    private boolean canBeSelfReassigned;
    private String startBtnText;
    private long activationTime;
    private long version;
    private boolean newOrderFlowEnabled;
    private AcceptanceTimer acceptanceTimer;
    private int reassignmentTimeLeftInSeconds;
    private Object pickupPointETA;
    private Object returnData;
    private Object notification;
    private boolean richNavigationAPIEnabled;
    private List<Object> benefits;


    public Delivery(long id, long orderId, List<Long> orderIds, String urn, List<Point> points, String totalDistance, String totalDistanceDescription, String totalCompensation, int compensationInMinimumRep, int currencyDecimals, int distanceInMinimumRep, String bonusCoefficient, List<Object> courierPaymentBreakdown, boolean isBoosted, String distanceBasedEarnings, double maxBillAmount, List<Instruction> instructions, List<String> paymentHints, int numberOfOrders, String deliveryCode, String deliveryType, boolean isNew, boolean reassignmentRequested, Integer secondsToExpire, Integer acceptanceTimeStamp, boolean isStarted, boolean canBeSelfReassigned, String startBtnText, long activationTime, long version, boolean newOrderFlowEnabled, AcceptanceTimer acceptanceTimer, int reassignmentTimeLeftInSeconds, Object pickupPointETA, Object returnData, Object notification, boolean richNavigationAPIEnabled, List<Object> benefits) {
        this.id = id;
        this.orderId = orderId;
        this.orderIds = orderIds;
        this.urn = urn;
        this.points = points;
        this.totalDistance = totalDistance;
        this.totalDistanceDescription = totalDistanceDescription;
        this.totalCompensation = totalCompensation;
        this.compensationInMinimumRep = compensationInMinimumRep;
        this.currencyDecimals = currencyDecimals;
        this.distanceInMinimumRep = distanceInMinimumRep;
        this.bonusCoefficient = bonusCoefficient;
        this.courierPaymentBreakdown = courierPaymentBreakdown;
        this.isBoosted = isBoosted;
        this.distanceBasedEarnings = distanceBasedEarnings;
        this.maxBillAmount = maxBillAmount;
        this.instructions = instructions;
        this.paymentHints = paymentHints;
        this.numberOfOrders = numberOfOrders;
        this.deliveryCode = deliveryCode;
        this.deliveryType = deliveryType;
        this.isNew = isNew;
        this.reassignmentRequested = reassignmentRequested;
        this.secondsToExpire = secondsToExpire;
        this.acceptanceTimeStamp = acceptanceTimeStamp;
        this.isStarted = isStarted;
        this.canBeSelfReassigned = canBeSelfReassigned;
        this.startBtnText = startBtnText;
        this.activationTime = activationTime;
        this.version = version;
        this.newOrderFlowEnabled = newOrderFlowEnabled;
        this.acceptanceTimer = acceptanceTimer;
        this.reassignmentTimeLeftInSeconds = reassignmentTimeLeftInSeconds;
        this.pickupPointETA = pickupPointETA;
        this.returnData = returnData;
        this.notification = notification;
        this.richNavigationAPIEnabled = richNavigationAPIEnabled;
        this.benefits = benefits;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public String getUrn() {
        return urn;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTotalDistanceDescription() {
        return totalDistanceDescription;
    }

    public void setTotalDistanceDescription(String totalDistanceDescription) {
        this.totalDistanceDescription = totalDistanceDescription;
    }

    public String getTotalCompensation() {
        return totalCompensation;
    }

    public void setTotalCompensation(String totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    public int getCompensationInMinimumRep() {
        return compensationInMinimumRep;
    }

    public void setCompensationInMinimumRep(int compensationInMinimumRep) {
        this.compensationInMinimumRep = compensationInMinimumRep;
    }

    public int getCurrencyDecimals() {
        return currencyDecimals;
    }

    public void setCurrencyDecimals(int currencyDecimals) {
        this.currencyDecimals = currencyDecimals;
    }

    public int getDistanceInMinimumRep() {
        return distanceInMinimumRep;
    }

    public void setDistanceInMinimumRep(int distanceInMinimumRep) {
        this.distanceInMinimumRep = distanceInMinimumRep;
    }

    public String getBonusCoefficient() {
        return bonusCoefficient;
    }

    public void setBonusCoefficient(String bonusCoefficient) {
        this.bonusCoefficient = bonusCoefficient;
    }

    public List<Object> getCourierPaymentBreakdown() {
        return courierPaymentBreakdown;
    }

    public void setCourierPaymentBreakdown(List<Object> courierPaymentBreakdown) {
        this.courierPaymentBreakdown = courierPaymentBreakdown;
    }

    public boolean isBoosted() {
        return isBoosted;
    }

    public void setBoosted(boolean boosted) {
        isBoosted = boosted;
    }

    public String getDistanceBasedEarnings() {
        return distanceBasedEarnings;
    }

    public void setDistanceBasedEarnings(String distanceBasedEarnings) {
        this.distanceBasedEarnings = distanceBasedEarnings;
    }

    public double getMaxBillAmount() {
        return maxBillAmount;
    }

    public void setMaxBillAmount(double maxBillAmount) {
        this.maxBillAmount = maxBillAmount;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<String> getPaymentHints() {
        return paymentHints;
    }

    public void setPaymentHints(List<String> paymentHints) {
        this.paymentHints = paymentHints;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public boolean isReassignmentRequested() {
        return reassignmentRequested;
    }

    public void setReassignmentRequested(boolean reassignmentRequested) {
        this.reassignmentRequested = reassignmentRequested;
    }

    public Integer getSecondsToExpire() {
        return secondsToExpire;
    }

    public void setSecondsToExpire(Integer secondsToExpire) {
        this.secondsToExpire = secondsToExpire;
    }

    public double getAcceptanceTimeStamp() {
        return acceptanceTimeStamp;
    }

    public void setAcceptanceTimeStamp(double acceptanceTimeStamp) {
        this.acceptanceTimeStamp = acceptanceTimeStamp;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isCanBeSelfReassigned() {
        return canBeSelfReassigned;
    }

    public void setCanBeSelfReassigned(boolean canBeSelfReassigned) {
        this.canBeSelfReassigned = canBeSelfReassigned;
    }

    public String getStartBtnText() {
        return startBtnText;
    }

    public void setStartBtnText(String startBtnText) {
        this.startBtnText = startBtnText;
    }

    public long getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(long activationTime) {
        this.activationTime = activationTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isNewOrderFlowEnabled() {
        return newOrderFlowEnabled;
    }

    public void setNewOrderFlowEnabled(boolean newOrderFlowEnabled) {
        this.newOrderFlowEnabled = newOrderFlowEnabled;
    }

    public AcceptanceTimer getAcceptanceTimer() {
        return acceptanceTimer;
    }

    public void setAcceptanceTimer(AcceptanceTimer acceptanceTimer) {
        this.acceptanceTimer = acceptanceTimer;
    }

    public int getReassignmentTimeLeftInSeconds() {
        return reassignmentTimeLeftInSeconds;
    }

    public void setReassignmentTimeLeftInSeconds(int reassignmentTimeLeftInSeconds) {
        this.reassignmentTimeLeftInSeconds = reassignmentTimeLeftInSeconds;
    }

    public Object getPickupPointETA() {
        return pickupPointETA;
    }

    public void setPickupPointETA(Object pickupPointETA) {
        this.pickupPointETA = pickupPointETA;
    }

    public Object getReturnData() {
        return returnData;
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

    public Object getNotification() {
        return notification;
    }

    public void setNotification(Object notification) {
        this.notification = notification;
    }

    public boolean isRichNavigationAPIEnabled() {
        return richNavigationAPIEnabled;
    }

    public void setRichNavigationAPIEnabled(boolean richNavigationAPIEnabled) {
        this.richNavigationAPIEnabled = richNavigationAPIEnabled;
    }

    public List<Object> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Object> benefits) {
        this.benefits = benefits;
    }

    public Object getBanner() {
        return banner;
    }

    public void setBanner(Object banner) {
        this.banner = banner;
    }
}
