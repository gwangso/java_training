package repostitory;

import dto.AccountDTO;
import dto.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public class BankRepository {
    private List<ClientDTO> clientDTOList = new ArrayList<>();
    private List<AccountDTO> accountDTOList = new ArrayList<>();


    public boolean saveClinet(ClientDTO clientDTO) {
        return clientDTOList.add(clientDTO);
    }

    public boolean accountCheck(String accountNumber){
        for (ClientDTO clientDTO : clientDTOList){
            if(clientDTO.getAccountNumber().equals(accountNumber)){
                return false;
            }
        }
        return true;
    }

    public List<ClientDTO> findAll() {
        return clientDTOList;
    }

    public ClientDTO findbyAccount(String accountNumber) {
        for (ClientDTO clientDTO : clientDTOList) {
            if(clientDTO.getAccountNumber().equals(accountNumber)){
                return clientDTO;
            }
        }
        return null;
    }


    public boolean deposit(ClientDTO clientDTO, AccountDTO accountDTO) {
        clientDTO.setBalance(clientDTO.getBalance() + accountDTO.getDeposit());
        return accountDTOList.add(accountDTO);
    }

    public boolean withdraw(ClientDTO clientDTO, AccountDTO accountDTO) {
        clientDTO.setBalance(clientDTO.getBalance() - accountDTO.getWithdraw());
        return accountDTOList.add(accountDTO);
    }

    public List<AccountDTO> findAccountByAccount(String accountNumber) {
        List<AccountDTO> list = new ArrayList<>();
        for (AccountDTO accountDTO : accountDTOList){
            if(accountDTO.getAccountNumber().equals(accountNumber)){
                list.add(accountDTO);
            }
        }
        return list;
    }
}
