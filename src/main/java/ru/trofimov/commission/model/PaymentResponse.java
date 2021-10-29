package ru.trofimov.commission.model;

public class PaymentResponse {

    private final long phoneNumber;

    private final long monthlyPayments;

    private long commission;

    private String date;

    private final int month;

    private final int year;

    public PaymentResponse(long phoneNumber, long monthlyPayments, int month, int year) {
        this.phoneNumber = phoneNumber;
        this.monthlyPayments = monthlyPayments;
        this.month = month;
        this.year = year;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public long getMonthlyPayments() {
        return monthlyPayments;
    }

    public long getCommission() {
        if (monthlyPayments < 10000){
            commission = monthlyPayments / 100;
        }
        if (monthlyPayments >= 10000 && monthlyPayments < 100000){
            commission = monthlyPayments / 100 * 3;
        }
        if (monthlyPayments >= 100000 ){
            commission = monthlyPayments / 100 * 5;
        }
        return commission;
    }

    public String getDate() {
        switch (month) {
            case 0:
                date ="January";
                break;
            case 1:
                date ="February";
                break;
            case 2:
                date ="March";
                break;
            case 3:
                date ="April";
                break;
            case 4:
                date ="May";
                break;
            case 5:
                date ="June";
                break;
            case 6:
                date ="July";
                break;
            case 7:
                date ="August";
                break;
            case 8:
                date ="September";
                break;
            case 9:
                date ="October";
                break;
            case 10:
                date ="November";
                break;
            case 11:
                date ="December";
                break;
        }

        return date + " " + (year + 1900);
    }
}
