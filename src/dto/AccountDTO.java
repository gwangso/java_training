package dto;

public class AccountDTO {
    private Long id;
    private String accountNumber;
    private long deposit;
    private long withdraw;
    private String bankingAt;

    private static Long num2 = 1L;

    public AccountDTO(){
    }

    public AccountDTO(String accountNumber, Long deposit, Long withdraw) {
        this.id = num2++;
        this.accountNumber = accountNumber;
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.bankingAt = Util.createdAtGenerator();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getDeposit() {
        return deposit;
    }

    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public Long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Long withdraw) {
        this.withdraw = withdraw;
    }

    public String getBankingAt() {
        return bankingAt;
    }

    public void setBankingAt(String bankingAt) {
        this.bankingAt = bankingAt;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", deposit=" + deposit +
                ", withdraw=" + withdraw +
                ", bankingAt='" + bankingAt + '\'' +
                '}';
    }
}