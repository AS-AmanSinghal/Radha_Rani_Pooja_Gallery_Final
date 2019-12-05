package com.WeShowedUp.radharanipoojagallery.Lists;

public class Coupon {
    String couponno,from_date,from_time,to_date,to_time,amount;

    public String getCouponno() {
        return couponno;
    }

    public void setCouponno(String couponno) {
        this.couponno = couponno;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponno='" + couponno + '\'' +
                ", from_date='" + from_date + '\'' +
                ", from_time='" + from_time + '\'' +
                ", to_date='" + to_date + '\'' +
                ", to_time='" + to_time + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
