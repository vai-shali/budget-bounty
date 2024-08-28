package com.example.budget_bounty.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.budget_bounty.util.DatabaseConnection;

import model1.Bank;

public class BankRepository {

    // Method to get all bank accounts
    public List<Bank> getAllBanks() {
        List<Bank> banks = new ArrayList<>();
        String query = "SELECT * FROM Bank";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Bank bank = mapResultSetToBank(resultSet);
                banks.add(bank);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banks;
    }

    // Method to get a bank by bank ID
    public Bank getBankById(int bankId) {
        String query = "SELECT * FROM Bank WHERE bank_id = ?";
        Bank bank = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bankId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    bank = mapResultSetToBank(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank;
    }

    // Method to add a new bank account
    public void addBank(Bank bank) {
        String query = "INSERT INTO Bank (bank_id, bank_name, IFSC, account_number, upi_id, account_type, balance, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bank.getBankId());
            preparedStatement.setString(2, bank.getBankName());
            preparedStatement.setString(3, bank.getIfsc());
            preparedStatement.setString(4, bank.getAccountNumber());
            preparedStatement.setString(5, bank.getUpiId());
            preparedStatement.setString(6, bank.getAccountType());
            preparedStatement.setDouble(7, bank.getBalance());
            preparedStatement.setInt(8, bank.getUserId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update an existing bank account
    public void updateBank(Bank bank) {
        String query = "UPDATE Bank SET bank_name = ?, IFSC = ?, account_number = ?, upi_id = ?, account_type = ?, balance = ?, user_id = ? WHERE bank_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, bank.getBankName());
            preparedStatement.setString(2, bank.getIfsc());
            preparedStatement.setString(3, bank.getAccountNumber());
            preparedStatement.setString(4, bank.getUpiId());
            preparedStatement.setString(5, bank.getAccountType());
            preparedStatement.setDouble(6, bank.getBalance());
            preparedStatement.setInt(7, bank.getUserId());
            preparedStatement.setInt(8, bank.getBankId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a bank account by ID
    public void deleteBank(int bankId) {
        String query = "DELETE FROM Bank WHERE bank_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bankId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to map ResultSet to Bank object
    private Bank mapResultSetToBank(ResultSet resultSet) throws SQLException {
        return new Bank(
            resultSet.getInt("bank_id"),
            resultSet.getString("bank_name"),
            resultSet.getString("IFSC"),
            resultSet.getString("account_number"),
            resultSet.getString("upi_id"),
            resultSet.getString("account_type"),
            resultSet.getDouble("balance"),
            resultSet.getInt("user_id")
        );
    }
}

