package com.example.approval_service.dto;

public class BalanceUpdateData {

    private Integer employee_id;

    private Integer new_balance;

    public BalanceUpdateData() {
    }

    public BalanceUpdateData(Integer employee_id, Integer new_balance) {
        this.employee_id = employee_id;
        this.new_balance = new_balance;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getNew_balance() {
        return new_balance;
    }

    public void setNew_balance(Integer new_balance) {
        this.new_balance = new_balance;
    }

    @Override
    public String toString() {
        return "BalanceUpdateData{" +
                "employee_id=" + employee_id +
                ", new_balance=" + new_balance +
                '}';
    }
}
