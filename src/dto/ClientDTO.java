package dto;

public class ClientDTO {
    private Long id;
    private String clientName;
    private String accountNumber;
    private String clientPass;
    private String clientCreateedAt;
    private long balance = 0;
    private static Long num1 = 1L;

    public ClientDTO(){
    }

    public ClientDTO(String clientName, String accountNumber, String clientPass) {
        this.id = num1++;
        this.clientName = clientName;
        this.accountNumber = accountNumber;
        this.clientPass = clientPass;
        this.clientCreateedAt = Util.createdAtGenerator();
    }

    public ClientDTO(String clientName, String accountNumber, String clientPass, String clientCreateedAt, Long balance) {
        this.id = num1++;
        this.clientName = clientName;
        this.accountNumber = accountNumber;
        this.clientPass = clientPass;
        this.clientCreateedAt = clientCreateedAt;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getClientPass() {
        return clientPass;
    }

    public void setClientPass(String clientPass) {
        this.clientPass = clientPass;
    }

    public String getClientCreateedAt() {
        return clientCreateedAt;
    }

    public void setClientCreateedAt(String clientCreateedAt) {
        this.clientCreateedAt = clientCreateedAt;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", clientPass='" + clientPass + '\'' +
                ", clientCreateedAt='" + clientCreateedAt + '\'' +
                ", balance=" + balance +
                '}';
    }
}
